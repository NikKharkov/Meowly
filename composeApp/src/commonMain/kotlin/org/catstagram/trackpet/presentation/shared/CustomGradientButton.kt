package org.catstagram.trackpet.presentation.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.presentation.theme.PrimaryGradient
import org.catstagram.trackpet.util.pressAnimation

@Composable
fun CustomGradientButton(
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    modifier: Modifier = Modifier
) {
    val dimensions = LocalAppDimensions.current

    Surface(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = dimensions.paddingMedium, vertical = dimensions.paddingSmall)
            .pressAnimation(enabled = enabled),
        shape = RoundedCornerShape(dimensions.paddingSmall),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = if (enabled) PrimaryGradient else SolidColor(Color.Gray),
                    shape = RoundedCornerShape(dimensions.paddingSmall)
                )
                .padding(vertical = dimensions.paddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                color = if (enabled) Color.White else Color.White.copy(alpha = 0.5f),
                fontWeight = FontWeight.Medium
            )
        }
    }
}