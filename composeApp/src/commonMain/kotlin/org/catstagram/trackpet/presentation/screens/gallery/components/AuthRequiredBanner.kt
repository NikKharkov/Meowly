package org.catstagram.trackpet.presentation.screens.gallery.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.go_to_settings
import catstagrammp.composeapp.generated.resources.pls_login
import org.catstagram.trackpet.presentation.shared.CustomGradientButton
import org.catstagram.trackpet.presentation.shared.LottieAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource

@Composable
fun AuthRequiredBanner(
    animationSize: Dp,
    toSettingsScreen: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            fileName = "cat_with_laptop.lottie",
            modifier = Modifier.size(animationSize)
        )

        Spacer(modifier = Modifier.height(dimensions.paddingSmall))

        Text(
            text = stringResource(Res.string.pls_login),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colors.secondary
        )

        Spacer(modifier = Modifier.height(dimensions.paddingSmall))

        CustomGradientButton(
            text = stringResource(Res.string.go_to_settings),
            onClick = toSettingsScreen,
            modifier = Modifier
                .width(200.dp)
                .height(70.dp)
        )
    }
}