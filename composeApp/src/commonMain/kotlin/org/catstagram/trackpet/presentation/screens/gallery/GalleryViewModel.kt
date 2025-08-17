package org.catstagram.trackpet.presentation.screens.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.data.gallery.FirebaseStorageService
import org.catstagram.trackpet.data.gallery.ShareManager
import org.catstagram.trackpet.domain.gallery.GalleryState
import org.catstagram.trackpet.managers.auth.AuthenticationManager
import org.catstagram.trackpet.util.GALLERY_DELETE_IMAGE
import org.catstagram.trackpet.util.GALLERY_SAVE_IMAGE
import org.catstagram.trackpet.util.GALLERY_SHARE_IMAGE

class GalleryViewModel(
    private val storageService: FirebaseStorageService,
    private val authManager: AuthenticationManager
): ViewModel() {

    private val _galleryState = MutableStateFlow<GalleryState<Map<Long, List<String>>>>(GalleryState.success(emptyMap()))
    val galleryState: StateFlow<GalleryState<Map<Long, List<String>>>> = _galleryState.asStateFlow()

    private val _uploadingCount = MutableStateFlow(0)

    fun saveImages(byteArrays: List<ByteArray>, catId: Long) {
        println("$TAG: saveImages() called for catId=$catId, ${byteArrays.size} images")

        if (!authManager.isUserSignedIn()) {
            println("$TAG: User not authenticated, cannot save images")
            _galleryState.value = GalleryState.notAuthenticated()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentImages = _galleryState.value.data?.get(catId) ?: emptyList()
                val availableSlots = 10 - currentImages.size

                if (availableSlots <= 0) {
                    _galleryState.value = GalleryState.error("Upgrade to premium")
                    return@launch
                }

                val imagesToUpload = byteArrays.take(availableSlots)

                if (imagesToUpload.size < byteArrays.size) {
                    println("$TAG: Uploading only ${imagesToUpload.size} images due to limit")
                }

                _uploadingCount.value = imagesToUpload.size

                val uploadedUrls = mutableListOf<String>()

                for ((index, byteArray) in imagesToUpload.withIndex()) {
                    try {
                        println("$TAG: Uploading image ${index + 1}/${imagesToUpload.size}")
                        val url = storageService.uploadImage(byteArray, catId)
                        uploadedUrls.add(url)
                        println("$TAG: Successfully uploaded image ${index + 1}, URL: $url")

                        _uploadingCount.value = imagesToUpload.size - (index + 1)

                    } catch (e: Exception) {
                        println("$TAG: Failed to upload image ${index + 1}: ${e.message}")
                    }
                }

                println("$TAG: All uploads completed, ${uploadedUrls.size} URLs received")

                loadImages(catId)
                _uploadingCount.value = 0
                Analytics.trackFeatureUsed(GALLERY_SAVE_IMAGE)
            } catch (e: Exception) {
                println("$TAG: saveImages() failed with exception: ${e.message}")
                e.printStackTrace()
                _galleryState.value = GalleryState.error(e.message ?: "Upload failed")
                _uploadingCount.value = 0
            }
        }
    }

    fun loadImages(catId: Long) {
        println("$TAG: loadImages() called for catId=$catId")

        if (!authManager.isUserSignedIn()) {
            println("$TAG: User not authenticated, cannot load images")
            _galleryState.value = GalleryState.notAuthenticated()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val currentImages = _galleryState.value.data?.get(catId)
                if (!currentImages.isNullOrEmpty()) {
                    println("$TAG: Images already loaded for catId=$catId, skipping...")
                    return@launch
                }

                val currentData = _galleryState.value.data ?: emptyMap()
                println("$TAG: Current data keys: ${currentData.keys}")

                _galleryState.value = _galleryState.value.copy(isLoading = true)

                println("$TAG: Calling storageService.getImages...")
                val images = storageService.getImages(catId)
                println("$TAG: Got ${images.size} images from storage")

                val newMap = currentData + (catId to images)
                println("$TAG: New map will have ${newMap.size} entries")

                _galleryState.value = GalleryState.success(newMap)
                println("$TAG: loadImages() completed successfully")

            } catch (e: Exception) {
                println("$TAG: loadImages() failed with exception: ${e.message}")
                e.printStackTrace()
                _galleryState.value = _galleryState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Loading failed"
                )
            }
        }
    }

    fun deleteImages(imageUrls: List<String>, catId: Long) {
        println("$TAG: deleteImages() called for ${imageUrls.size} images, catId=$catId")

        if (!authManager.isUserSignedIn()) {
            println("$TAG: User not authenticated, cannot delete images")
            _galleryState.value = GalleryState.notAuthenticated()
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _galleryState.value = _galleryState.value.copy(isLoading = true)

                val deleteResults = imageUrls.map { url ->
                    async {
                        println("$TAG: Deleting image: $url")
                        val result = storageService.deleteImage(url)
                        println("$TAG: Delete result for $url: $result")
                        result
                    }
                }.awaitAll()

                val successCount = deleteResults.count { it }
                val failedCount = deleteResults.count { !it }

                println("$TAG: Deletion completed - Success: $successCount, Failed: $failedCount")

                if (failedCount > 0) {
                    println("$TAG: Some images failed to delete, but continuing...")
                }

                loadImages(catId)
                Analytics.trackFeatureUsed(GALLERY_DELETE_IMAGE)
            } catch (e: Exception) {
                println("$TAG: deleteImages() failed with exception: ${e.message}")
                e.printStackTrace()
                _galleryState.value = _galleryState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Delete failed"
                )
            }
        }
    }

    fun shareImages(imageUrls: List<String>, shareManager: ShareManager) {
        println("$TAG: shareImagesWithManager() called for ${imageUrls.size} images")

        viewModelScope.launch {
            try {
                val downloadedImages = withContext(Dispatchers.IO) {
                    imageUrls.mapNotNull { url ->
                        storageService.downloadImage(url)
                    }
                }

                println("$TAG: Downloaded ${downloadedImages.size} images, starting share...")

                if (downloadedImages.isNotEmpty()) {
                    val result = shareManager.shareImages(downloadedImages)
                    Analytics.trackFeatureUsed(GALLERY_SHARE_IMAGE)
                    println("$TAG: Share result: $result")
                } else {
                    println("$TAG: No images downloaded")
                }

            } catch (e: Exception) {
                println("$TAG: shareImagesWithManager() failed: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val TAG = "GalleryViewModel"
    }
}