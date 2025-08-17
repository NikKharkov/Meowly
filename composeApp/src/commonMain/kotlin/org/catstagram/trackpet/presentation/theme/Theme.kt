package org.catstagram.trackpet.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel

@Immutable
data class CatstagramColors(
    val background: Color,
    val onBackground: Color,
    val primary: Color,
    val secondary: Color,
    val accent: Color,
    val catCardGradient: Brush
)

@Immutable
data class CatstagramDimensions(
    val paddingTiny: Dp = 4.dp,
    val paddingSmall: Dp = 8.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 24.dp,
    val paddingXLarge: Dp = 32.dp,
)

val LightAppColors = CatstagramColors(
    background = Color(0xFFFCF6F1),
    onBackground = Color.Black,
    primary = Color.White,
    secondary = Color(0xFF8A8175),
    accent = SoftPink,
    catCardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFFF8F4F0),
            Color(0xFFF5E6E8),
            Color(0xFFF0F0F6)
        )
    )
)

val DarkAppColors = CatstagramColors(
    background = Color(0xFF1A1625),
    onBackground = Color.White,
    primary = Color(0xFF1F2937),
    secondary = Color(0xFF9CA3AF),
    accent = Color.White,
    catCardGradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF2A2438),
            Color(0xFF1F1B2E),
            Color(0xFF252140)
        )
    )
)

expect object LocalAppLocale {
    val current: String @Composable get
    @Composable
    infix fun provides(value: String?): ProvidedValue<*>
}

val LocalAppColors = compositionLocalOf { LightAppColors }
val LocalAppDimensions = compositionLocalOf { CatstagramDimensions() }

@Composable
fun CatstagramTheme(
    settingsViewModel: SettingsViewModel,
    content: @Composable () -> Unit
) {
    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()
    val colors = if (settingsUiState.isDarkTheme) DarkAppColors else LightAppColors
    val dimensions = CatstagramDimensions()

    val currentLocale = settingsUiState.language.code

    CompositionLocalProvider(
        LocalAppColors provides colors,
        LocalAppDimensions provides dimensions,
        LocalAppLocale provides currentLocale
    ) {
        key(currentLocale) {
            content()
        }
    }
}