package org.catstagram.trackpet.presentation.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val SoftPink = Color(0xFFF7B6B2)
val SoftBlue = Color(0xFFA3B9E5)
val AccentColor = Color(0xFF6366F1)
val SecondaryAccent = Color(0xFF8B5CF6)

val PrimaryGradient = Brush.linearGradient(
    colors = listOf(
        Color(0xFFA3B9E5),
        Color(0xFFF7B6B2)
    )
)

val AvatarBackgroundGradient = Brush.radialGradient(
    colors = listOf(
        SoftPink.copy(alpha = 0.3f),
        SoftBlue.copy(alpha = 0.2f)
    )
)

val ShadowGradient = Brush.verticalGradient(
    colors = listOf(
        Color.Transparent,
        Color.Black.copy(alpha = 0.3f)
    )
)