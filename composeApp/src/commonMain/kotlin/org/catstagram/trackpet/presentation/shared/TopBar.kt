package org.catstagram.trackpet.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.default_user_avatar
import catstagrammp.composeapp.generated.resources.ic_arrow_back
import coil3.compose.AsyncImage
import org.catstagram.trackpet.presentation.theme.AvatarBackgroundGradient
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    toSettingsScreen: () -> Unit,
    userAvatarURL: String?
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(colors.background)
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RoundedIcon(
            iconPainterRes = painterResource(Res.drawable.ic_arrow_back),
            onClick = onBackClick
        )

        if (userAvatarURL != null) {
            AsyncImage(
                model = userAvatarURL,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .pressAnimation(onClick = toSettingsScreen)
                    .background(brush = AvatarBackgroundGradient),
                contentScale = ContentScale.Crop,
            )
        } else {
            Image(
                painter = painterResource(Res.drawable.default_user_avatar),
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .pressAnimation(onClick = toSettingsScreen)
                    .background(brush = AvatarBackgroundGradient),
                contentScale = ContentScale.Crop,
            )
        }
    }
}