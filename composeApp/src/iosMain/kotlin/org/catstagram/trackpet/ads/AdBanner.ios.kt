package org.catstagram.trackpet.ads

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import org.catstagram.trackpet.generateIOSBanner

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun AdBanner(modifier: Modifier) {
    UIKitView(
        modifier = modifier
            .height(75.dp)
            .clip(RoundedCornerShape(16.dp)),
        factory = { generateIOSBanner().view }
    )
}