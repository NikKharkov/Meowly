package org.catstagram.trackpet.data.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import platform.Foundation.NSData
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.dataWithBytes
import platform.Foundation.writeToFile
import platform.UIKit.UIActivityViewController
import platform.UIKit.UIApplication

actual class ShareManager {

    actual suspend fun shareImages(images: List<ByteArray>): Result<Unit> {
        return runCatching {
            val urls = withContext(Dispatchers.IO) {
                images.mapIndexedNotNull { index, bytes ->
                    saveImageToTemp(bytes, "image_$index.jpg")
                }
            }

            val activityViewController = UIActivityViewController(urls, null)
            UIApplication.sharedApplication.keyWindow?.rootViewController?.presentViewController(
                activityViewController,
                animated = true,
                completion = null
            )
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun saveImageToTemp(bytes: ByteArray, fileName: String): NSURL? {
        val tempDir = NSTemporaryDirectory()
        val filePath = tempDir + fileName

        return bytes.usePinned { pinned ->
            val nsData = NSData.dataWithBytes(pinned.addressOf(0), bytes.size.toULong())
            if (nsData.writeToFile(filePath, true)) {
                NSURL.fileURLWithPath(filePath)
            } else null
        }
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    return remember { ShareManager() }
}