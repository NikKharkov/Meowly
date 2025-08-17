package org.catstagram.trackpet.presentation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import dev.gitlive.firebase.auth.FirebaseUser
import org.catstagram.trackpet.domain.settings.Language
import org.catstagram.trackpet.domain.settings.SettingsUiState
import org.catstagram.trackpet.presentation.screens.settings.components.AboutSection
import org.catstagram.trackpet.presentation.screens.settings.components.LanguageSelectionDialog
import org.catstagram.trackpet.presentation.screens.settings.components.LoginButtonsSection
import org.catstagram.trackpet.presentation.screens.settings.components.NotificationPermissionDialog
import org.catstagram.trackpet.presentation.screens.settings.components.UserAvatar
import org.catstagram.trackpet.presentation.screens.settings.components.UserPreferencesSection
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SettingsScreen(
    settingsViewModel: SettingsViewModel,
    onPrivacyClick: () -> Unit
) {
    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()

    var showLanguageDialog by remember { mutableStateOf(false) }
    var showPermissionDialog by remember { mutableStateOf(false) }

    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                settingsViewModel.loadSettings()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp

        SettingsScreenComponents(
            handleAuth = { result ->
                settingsViewModel.handleAuthResult(result)
            },
            onLogOutClick = {
                settingsViewModel.signOut()
            },
            onThemeChange = {
                settingsViewModel.changeTheme(!settingsUiState.isDarkTheme)
            },
            onLanguageChange = { language ->
                settingsViewModel.changeLanguage(language)
            },
            onNotificationsChange = { enabled ->
                settingsViewModel.toggleNotifications(
                    enabled = enabled,
                    onPermissionDeniedForever = { showPermissionDialog = true }
                )
            },
            onOpenSettings = { settingsViewModel.openAppSettings() },
            settingsUiState = settingsUiState,
            isTablet = isTablet,
            showLanguageDialog = showLanguageDialog,
            onShowLanguageDialog = { show -> showLanguageDialog = show },
            showPermissionDialog = showPermissionDialog,
            onShowPermissionDialog = { show -> showPermissionDialog = show },
            onPrivacyClick = onPrivacyClick
        )
    }
}

@Composable
private fun SettingsScreenComponents(
    handleAuth: (Result<FirebaseUser?>) -> Unit,
    onLogOutClick: () -> Unit,
    onThemeChange: (Boolean) -> Unit,
    onLanguageChange: (Language) -> Unit,
    onNotificationsChange: (Boolean) -> Unit,
    onOpenSettings: () -> Unit,
    settingsUiState: SettingsUiState,
    isTablet: Boolean,
    showLanguageDialog: Boolean,
    onShowLanguageDialog: (Boolean) -> Unit,
    showPermissionDialog: Boolean,
    onShowPermissionDialog: (Boolean) -> Unit,
    onPrivacyClick: () -> Unit
) {
    val colors = LocalAppColors.current
    val dimensions = LocalAppDimensions.current
    val maxWidth = if (isTablet) 600.dp else Dp.Unspecified
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colors.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .widthIn(max = maxWidth)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .windowInsetsPadding(WindowInsets.systemBars)
                .padding(contentPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(contentPadding)
        ) {
            UserAvatar(
                userAvatarURL = settingsUiState.user?.profilePicture,
                userNickname = settingsUiState.user?.name,
                isSigningOut = settingsUiState.isSigningOut
            )

            UserPreferencesSection(
                settingsUiState = settingsUiState,
                onThemeChange = onThemeChange,
                onLanguageClick = {
                    onShowLanguageDialog(true)
                },
                onNotificationsChange = onNotificationsChange
            )

            LoginButtonsSection(
                handleAuth = handleAuth,
                onLogOutClick = onLogOutClick
            )

            AboutSection(
                onPrivacyClick = onPrivacyClick
            )
        }
    }

    if (showLanguageDialog) {
        LanguageSelectionDialog(
            currentLanguage = settingsUiState.language,
            onLanguageSelected = { language ->
                onLanguageChange(language)
            },
            onDismiss = {
                onShowLanguageDialog(false)
            }
        )
    }

    if (showPermissionDialog) {
        NotificationPermissionDialog(
            onOpenSettings = {
                onOpenSettings()
                onShowPermissionDialog(false)
            },
            onDismiss = {
                onShowPermissionDialog(false)
            }
        )
    }
}

@Preview
@Composable
private fun SettingsScreenPreview() {
    SettingsScreenComponents(
        handleAuth = {},
        onLogOutClick = {},
        onThemeChange = {},
        onLanguageChange = {},
        onNotificationsChange = {},
        onOpenSettings = {},
        isTablet = false,
        settingsUiState = SettingsUiState(),
        showLanguageDialog = false,
        showPermissionDialog = false,
        onShowLanguageDialog = {},
        onShowPermissionDialog = {},
        onPrivacyClick = {}
    )
}