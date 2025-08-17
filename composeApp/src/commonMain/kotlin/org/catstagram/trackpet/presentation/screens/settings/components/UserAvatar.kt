package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.default_name
import catstagrammp.composeapp.generated.resources.default_user_avatar
import coil3.compose.AsyncImage
import org.catstagram.trackpet.presentation.shared.LottieAnimation
import org.catstagram.trackpet.presentation.theme.AvatarBackgroundGradient
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserAvatar(
    userAvatarURL: String?,
    userNickname: String?,
    isSigningOut: Boolean
) {
    val dimensions = LocalAppDimensions.current
    val colors = LocalAppColors.current

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
    ) {
        when {
            userAvatarURL != null -> {
                AsyncImage(
                    model = userAvatarURL,
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(brush = AvatarBackgroundGradient),
                    contentScale = ContentScale.Crop,
                )
            }

            isSigningOut -> {
                LottieAnimation(
                    fileName = "cat_loading.lottie",
                    modifier = Modifier.size(100.dp)
                )
            }

            else -> {
                Image(
                    painter = painterResource(Res.drawable.default_user_avatar),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(brush = AvatarBackgroundGradient),
                    contentScale = ContentScale.Crop,
                )
            }
        }

        Text(
            text = if (userNickname.isNullOrBlank()) stringResource(Res.string.default_name) else userNickname,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground
        )
    }
}