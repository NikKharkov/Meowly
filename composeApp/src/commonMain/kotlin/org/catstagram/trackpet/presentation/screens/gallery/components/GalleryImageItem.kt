package org.catstagram.trackpet.presentation.screens.gallery.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_check
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource

@Composable
fun GalleryImageItem(
    imageUrl: String,
    isSelected: Boolean,
    contentPadding: Dp,
    onImageLongPress: (String) -> Unit,
    onImageClick: (String) -> Unit
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color.Green.copy(alpha = 0.3f) else Color.Transparent,
        label = "selection_animation"
    )

    Box {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .width(200.dp)
                .height(250.dp)
                .background(backgroundColor)
                .pointerInput(imageUrl) {
                    detectTapGestures(
                        onLongPress = {
                            onImageLongPress(imageUrl)
                        },
                        onTap = {
                            onImageClick(imageUrl)
                        }
                    )
                }
                .padding(contentPadding)
        )

        if (isSelected) {
            Icon(
                painter = painterResource(Res.drawable.ic_check),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .background(Color.Green, CircleShape)
                    .padding(4.dp)
            )
        }
    }
}