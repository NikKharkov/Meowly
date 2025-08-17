package org.catstagram.trackpet.presentation.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.data.local.UserCat
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CatCard(
    saveImage: (Long, ByteArray?) -> Unit,
    onEditClick: (Long) -> Unit,
    toGalleryScreen: (Long) -> Unit,
    cat: UserCat
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .pressAnimation(
                scaleDown = 0.85f,
                alphaDown = 0.7f
            ),
        onClick = { toGalleryScreen(cat.id) },
        shape = RoundedCornerShape(dimensions.paddingMedium),
        shadowElevation = 6.dp,
        color = Color.Transparent
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(brush = colors.catCardGradient)
                .padding(dimensions.paddingMedium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            ProfilePicture(
                saveImage = saveImage,
                cat = cat
            )

            Spacer(modifier = Modifier.width(dimensions.paddingMedium))

            CatInfo(
                cat = cat,
                modifier = Modifier.weight(1f),
                onEditClick = onEditClick
            )
        }
    }
}

@Preview
@Composable
private fun CatCardPreview() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CatCard(
            saveImage = { _, _ -> },
            cat = UserCat(
                id = 1,
                name = "Shady",
                gender = "F",
                birthDate = 123L,
                avatarImg = null
            ),
            toGalleryScreen = {},
            onEditClick = {}
        )
    }
}