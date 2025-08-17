package org.catstagram.trackpet.util

import io.github.vinceglb.filekit.FileKit
import io.github.vinceglb.filekit.saveImageToGallery
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.readRawBytes
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class ImageSaver(private val httpClient: HttpClient) {

    @OptIn(ExperimentalUuidApi::class)
    suspend fun saveImageToGallery(imageUrl: String): Boolean {
        return try {
            val imageBytes = downloadImage(imageUrl) ?: return false
            val fileName = "Catstagram_AI_Image_${Uuid.Companion.random().toString().take(8)}.jpg"

            FileKit.saveImageToGallery(
                bytes = imageBytes,
                filename = fileName
            )
            true
        } catch (e: Exception) {
            println("Error while trying to save image: $e")
            false
        }
    }

    private suspend fun downloadImage(url: String): ByteArray? {
        return try {
            withContext(Dispatchers.IO) {
                val response = httpClient.get(url)
                response.readRawBytes()
            }
        } catch (e: Exception) {
            println("Error while trying to download image: $e")
            null
        }
    }
}