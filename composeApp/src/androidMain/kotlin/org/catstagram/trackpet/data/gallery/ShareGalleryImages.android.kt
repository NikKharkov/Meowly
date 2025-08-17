package org.catstagram.trackpet.data.gallery

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.core.content.FileProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.io.File

actual class ShareManager : KoinComponent {
    private val context: Context by inject()

    actual suspend fun shareImages(images: List<ByteArray>): Result<Unit> {
        println("ShareManager: shareImages called with ${images.size} images")
        return runCatching {
            withContext(Dispatchers.IO) {
                val uris = images.mapIndexed { index, bytes ->
                    val file = File(context.cacheDir, "image_$index.jpg")
                    file.writeBytes(bytes)
                    FileProvider.getUriForFile(
                        context,
                        "${context.packageName}.provider",
                        file
                    )
                }

                withContext(Dispatchers.Main) {
                    val intent = if (uris.size == 1) {
                        Intent(Intent.ACTION_SEND).apply {
                            putExtra(Intent.EXTRA_STREAM, uris.first())
                            type = "image/*"
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                    } else {
                        Intent(Intent.ACTION_SEND_MULTIPLE).apply {
                            putParcelableArrayListExtra(Intent.EXTRA_STREAM, ArrayList(uris))
                            type = "image/*"
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                    }

                    val chooser = Intent.createChooser(intent, null).apply {
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }

                    context.startActivity(chooser)
                    println("ShareManager: Share intent started successfully")
                }
            }
        }
    }
}

@Composable
actual fun rememberShareManager(): ShareManager {
    return remember { ShareManager() }
}