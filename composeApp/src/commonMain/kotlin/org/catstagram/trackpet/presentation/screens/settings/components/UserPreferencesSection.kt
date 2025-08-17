package org.catstagram.trackpet.presentation.screens.settings.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import catstagrammp.composeapp.generated.resources.Res
import catstagrammp.composeapp.generated.resources.preferences
import org.catstagram.trackpet.domain.settings.SettingsUiState
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.resources.stringResource

@Composable
fun UserPreferencesSection(
    settingsUiState: SettingsUiState,
    onThemeChange: (Boolean) -> Unit,
    onLanguageClick: () -> Unit,
    onNotificationsChange: (Boolean) -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingSmall)
    ) {
        Text(
            text = stringResource(Res.string.preferences),
            fontSize = 14.sp,
            color = colors.secondary
        )

        LanguageSettingRow(
            currentLanguage = settingsUiState.language,
            onLanguageClick = onLanguageClick
        )

        DarkThemeSettingRow(
            isDarkTheme = settingsUiState.isDarkTheme,
            onThemeChange = onThemeChange
        )

        NotificationsSettingRow(
            areNotificationsEnabled = settingsUiState.areNotificationsEnabled,
            onNotificationsChange = onNotificationsChange
        )
    }
}
