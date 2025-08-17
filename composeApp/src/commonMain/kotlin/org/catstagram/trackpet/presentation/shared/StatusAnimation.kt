package org.catstagram.trackpet.presentation.shared

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Status{
    ERROR,
    LOADING,
    EMPTY
}

@Composable
fun StatusAnimation(
    modifier: Modifier = Modifier,
    animationSize: Dp = 150.dp,
    status: Status
) {
    LottieAnimation(
        fileName = when(status) {
            Status.ERROR -> "cat_error.lottie"
            Status.LOADING -> "cat_loading.lottie"
            Status.EMPTY -> "cat_hiding.lottie"
        },
        modifier = modifier.size(animationSize)
    )
}