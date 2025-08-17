package org.catstagram.trackpet.presentation.screens.add_edit.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.welcome_text
import org.catstagram.trackpet.presentation.shared.LottieAnimation
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource

@Composable
fun WelcomeHeader(animationSize: Dp) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    LottieAnimation(
        fileName = "cat_welcoming.lottie",
        modifier = Modifier
            .size(animationSize)
            .padding(dimensions.paddingMedium)
    )

    Text(
        text = stringResource(Res.string.welcome_text),
        fontSize = 16.sp,
        color = colors.secondary,
        textAlign = TextAlign.Center
    )
}
