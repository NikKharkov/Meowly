package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_arrow_back
import catstagrammp.composeapp.generated.resources.ic_bookmark
import catstagrammp.composeapp.generated.resources.ic_bookmark_filled
import org.catstagram.trackpet.domain.handbook.CatBreed
import org.catstagram.trackpet.presentation.shared.RoundedIcon
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.ShadowGradient
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun CatDetailsTopBar(
    catBreed: CatBreed,
    isBookmarked: Boolean,
    onBookmarkClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalAppDimensions.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(catBreed.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = ShadowGradient)
        )

        RoundedIcon(
            iconPainterRes = painterResource(Res.drawable.ic_arrow_back),
            onClick = onBackClick,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(dimensions.paddingMedium)

        )

        RoundedIcon(
            iconPainterRes = painterResource(
                if (isBookmarked)
                    Res.drawable.ic_bookmark_filled else Res.drawable.ic_bookmark),
            onClick = onBookmarkClick,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(dimensions.paddingMedium)
        )

        Text(
            text = stringResource(catBreed.name),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp),
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black.copy(alpha = 0.7f),
                    offset = Offset(2f, 2f),
                    blurRadius = 4f
                )
            ),
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}
