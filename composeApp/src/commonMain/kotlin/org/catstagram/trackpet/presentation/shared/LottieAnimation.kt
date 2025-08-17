package org.catstagram.trackpet.presentation.shared

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import catstagrammp.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.DotLottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
@Composable
fun LottieAnimation(
    modifier: Modifier = Modifier,
    fileName: String,
    iterations: Int = Compottie.IterateForever
) {
    val composition by rememberLottieComposition {
        LottieCompositionSpec.DotLottie(
            Res.readBytes("files/$fileName")
        )
    }

    Image(
        painter = rememberLottiePainter(
            composition = composition,
            iterations = iterations
        ),
        contentDescription = null,
        modifier = modifier
    )
}