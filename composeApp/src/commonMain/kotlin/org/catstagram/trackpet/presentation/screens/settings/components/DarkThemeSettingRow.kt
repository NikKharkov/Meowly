package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.dark_theme
import catstagrammp.composeapp.generated.resources.ic_dark_mode
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun DarkThemeSettingRow(
    isDarkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
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
            .padding(vertical = dimensions.paddingSmall, horizontal = dimensions.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_dark_mode),
                contentDescription = null,
                tint = colors.onBackground,
                modifier = Modifier.size(24.dp)
            )

            Text(
                text = stringResource(Res.string.dark_theme),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = colors.onBackground
            )
        }

        Switch(
            checked = isDarkTheme,
            onCheckedChange = onThemeChange,
            colors = SwitchDefaults.colors(
                checkedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent,
                checkedThumbColor = colors.accent,
                checkedTrackColor = colors.accent.copy(alpha = 0.5f),
                uncheckedThumbColor = colors.secondary,
                uncheckedTrackColor = colors.secondary.copy(alpha = 0.3f)
            )
        )
    }
}