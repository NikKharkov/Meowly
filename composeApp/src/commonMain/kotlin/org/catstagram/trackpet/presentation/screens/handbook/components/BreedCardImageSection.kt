package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.ShadowGradient

@Composable
fun BreedCardImageSection(
    catImage: Painter,
    breedName: String,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalAppDimensions.current

    Box(
        modifier = modifier.clip(
            shape = RoundedCornerShape(
                topStart = dimensions.paddingMedium,
                topEnd = dimensions.paddingMedium
            )
        )
    ) {
        Image(
            painter = catImage,
            contentDescription = breedName,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ShadowGradient)
        )
    }
}