package org.catstagram.trackpet.presentation.screens.handbook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation

@Composable
fun CatBreedCard(
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
    catId: Int,
    catImage: Painter,
    catBreedName: String,
    personality: String,
    coat: String,
    size: String,
    isTablet: Boolean
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isTablet) 240.dp else 200.dp)
            .pressAnimation(
                scaleDown = 0.95f,
                alphaDown = 0.85f
            ),
        onClick = { onClick(catId) },
        shape = RoundedCornerShape(dimensions.paddingMedium),
        shadowElevation = 8.dp,
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = colors.catCardGradient)
        ) {
            BreedCardImageSection(
                catImage = catImage,
                breedName = catBreedName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (isTablet) 160.dp else 120.dp)
            )

            BreedCardDescSection(
                breedName = catBreedName,
                personality = personality,
                coat = coat,
                size = size,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(dimensions.paddingMedium)
            )
        }
    }
}