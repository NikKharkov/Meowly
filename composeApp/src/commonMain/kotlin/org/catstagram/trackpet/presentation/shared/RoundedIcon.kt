package org.catstagram.trackpet.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.util.pressAnimation

@Composable
fun RoundedIcon(
    modifier: Modifier = Modifier,
    iconPainterRes: Painter,
    onClick: () -> Unit
) {
    val colors = LocalAppColors.current

    Box(
        modifier = modifier
            .size(48.dp)
            .pressAnimation(onClick = onClick)
            .background(color = colors.onBackground.copy(alpha = 0.1f), shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = iconPainterRes,
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier.size(24.dp)
        )
    }
}