package org.catstagram.trackpet.data.gallery

import androidx.compose.runtime.Composable

expect class ShareManager {
    suspend fun shareImages(images: List<ByteArray>): Result<Unit>
}

@Composable
expect fun rememberShareManager(): ShareManager