package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.ic_arrow_forward_ios
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.catstagram.trackpet.util.pressAnimation
import org.jetbrains.compose.resources.painterResource

@Composable
fun RowWithIcon(
    onClick: () -> Unit,
    icon: Painter,
    iconColor: Color = Color.Unspecified,
    text: String
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colors.primary,
                shape = RoundedCornerShape(dimensions.paddingMedium)
            )
            .pressAnimation(onClick = { onClick() })
            .padding(dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Icon(
                painter = icon,
                contentDescription = null,
                tint = iconColor,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = text,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )
        }

        Icon(
            painter = painterResource(Res.drawable.ic_arrow_forward_ios),
            contentDescription = null,
            tint = colors.onBackground,
            modifier = Modifier.size(16.dp)
        )
    }
}