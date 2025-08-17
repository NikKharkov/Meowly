package org.catstagram.trackpet.data.gallery

import dev.gitlive.firebase.storage.StorageReference
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readRawBytes
import io.ktor.http.isSuccess
import org.catstagram.trackpet.managers.auth.AuthenticationManager
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

const val TAG = "FirebaseStorageService"

class FirebaseStorageService(
    private val storageReference: StorageReference,
    private val authManager: AuthenticationManager,
    private val httpClient: HttpClient
) {
    @OptIn(ExperimentalTime::class)
    suspend fun uploadImage(byteArray: ByteArray, catId: Long): String {
        println("$TAG: uploadImage() started for catId=$catId, byteArray size=${byteArray.size}")
        try {
            val userId = authManager.getCurrentUser()?.id

            println("$TAG: Using userId=$userId")
            val timestamp = Clock.System.now().epochSeconds
            val path = "$userId/$catId/$timestamp.jpg"
            println("$TAG: Upload path created: $path")
            val imageRef = storageReference.child(path)
            println("$TAG: Storage reference created, starting upload...")
            imageRef.putData(byteArray.toData())
            println("$TAG: Upload completed successfully")
            val downloadUrl = imageRef.getDownloadUrl()
            println("$TAG: Download URL obtained: $downloadUrl")
            return downloadUrl
        } catch (e: Exception) {
            println("$TAG: uploadImage() failed with exception: ${e.message}")
            println("$TAG: Exception type: ${e::class.simpleName}")
            e.printStackTrace()
            throw e
        }
    }

    suspend fun getImages(catId: Long): List<String> {
        println("$TAG: getImages() started for catId=$catId")
        try {
            val userId = authManager.getCurrentUser()?.id

            println("$TAG: Using userId=$userId for getting images")
            val path = "$userId/$catId"
            println("$TAG: Listing files in path: $path")
            val listResult = storageReference.child(path).listAll()
            println("$TAG: Found ${listResult.items.size} items in storage")
            val urls = listResult.items.mapIndexed { index, item ->
                println("$TAG: Getting download URL for item $index: ${item.path}")
                val url = item.getDownloadUrl()
                println("$TAG: URL $index: $url")
                url
            }
            println("$TAG: getImages() completed successfully, returning ${urls.size} URLs")
            return urls
        } catch (e: Exception) {
            println("$TAG: getImages() failed with exception: ${e.message}")
            println("$TAG: Exception type: ${e::class.simpleName}")
            e.printStackTrace()
            throw e
        }
    }

    suspend fun downloadImage(imageUrl: String): ByteArray? {
        println("$TAG: downloadImage() started for URL: $imageUrl")
        try {
            val response = httpClient.get(imageUrl)

            return if (response.status.isSuccess()) {
                val bytes = response.readRawBytes()
                println("$TAG: Downloaded ${bytes.size} bytes via Ktor")
                bytes
            } else {
                println("$TAG: HTTP error: ${response.status}")
                null
            }

        } catch (e: Exception) {
            println("$TAG: downloadImage() failed: ${e.message}")
            e.printStackTrace()
            return null
        }
    }

    suspend fun deleteImage(imageUrl: String): Boolean {
        println("$TAG: deleteImage() started for URL: $imageUrl")
        try {
            val userId = authManager.getCurrentUser()?.id

            val path = extractPathFromUrl(imageUrl)
            println("$TAG: Extracted path: $path")

            val pathForStorage = if (path.startsWith("images/")) {
                path.removePrefix("images/")
            } else {
                path
            }

            val belongsToUser = pathForStorage.startsWith(userId ?: "")
            if (!belongsToUser) {
                println("$TAG: Path doesn't belong to current user. UserId: $userId, Path: $pathForStorage")
                return false
            }

            val imageRef = storageReference.child(pathForStorage)
            println("$TAG: Got reference from URL: ${imageRef.path}")

            imageRef.delete()
            println("$TAG: Image deleted successfully")
            return true

        } catch (e: Exception) {
            println("$TAG: deleteImage() failed: ${e.message}")
            e.printStackTrace()
            return false
        }
    }

    private fun extractPathFromUrl(downloadUrl: String): String {
        println("$TAG: extractPathFromUrl() called with: $downloadUrl")

        return try {
            when {
                downloadUrl.contains("/o/") -> {
                    val encodedPath = downloadUrl.substringAfter("/o/").substringBefore("?")
                    val decodedPath = decodeUrlPath(encodedPath)
                    println("$TAG: Extracted path from standard URL: $decodedPath")
                    decodedPath
                }
                downloadUrl.contains("gs://") -> {
                    val path = downloadUrl.substringAfter("gs://").substringAfter("/")
                    println("$TAG: Extracted path from gs:// URL: $path")
                    path
                }
                else -> {
                    println("$TAG: URL doesn't match expected format, using as-is: $downloadUrl")
                    downloadUrl
                }
            }
        } catch (e: Exception) {
            println("$TAG: Failed to extract path from URL: $downloadUrl, error: ${e.message}")
            throw IllegalArgumentException("Invalid Firebase Storage URL: $downloadUrl", e)
        }
    }

    private fun decodeUrlPath(encodedPath: String): String {
        return encodedPath
            .replace("%2F", "/")
            .replace("%20", " ")
            .replace("%21", "!")
            .replace("%22", "\"")
            .replace("%23", "#")
            .replace("%24", "$")
            .replace("%25", "%")
            .replace("%26", "&")
            .replace("%27", "'")
            .replace("%28", "(")
            .replace("%29", ")")
            .replace("%2A", "*")
            .replace("%2B", "+")
            .replace("%2C", ",")
            .replace("%2D", "-")
            .replace("%2E", ".")
            .replace("%3A", ":")
            .replace("%3B", ";")
            .replace("%3C", "<")
            .replace("%3D", "=")
            .replace("%3E", ">")
            .replace("%3F", "?")
            .replace("%40", "@")
            .replace("%5B", "[")
            .replace("%5C", "\\")
            .replace("%5D", "]")
            .replace("%5E", "^")
            .replace("%5F", "_")
            .replace("%60", "`")
            .replace("%7B", "{")
            .replace("%7C", "|")
            .replace("%7D", "}")
            .replace("%7E", "~")
    }
}