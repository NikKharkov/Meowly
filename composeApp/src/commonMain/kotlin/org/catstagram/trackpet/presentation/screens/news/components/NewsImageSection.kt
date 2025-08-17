package org.catstagram.trackpet.presentation.screens.news.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_image
import coil3.compose.AsyncImage
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.ShadowGradient
import org.catstagram.trackpet.util.BASE_URL
import org.jetbrains.compose.resources.painterResource

@Composable
fun NewsImageSection(
    imageUrl: String?,
    title: String,
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
        AsyncImage(
            model = "$BASE_URL$imageUrl",
            contentDescription = title,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            placeholder = painterResource(Res.drawable.ic_image),
            error = painterResource(Res.drawable.ic_image)
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ShadowGradient)
        )
    }
}