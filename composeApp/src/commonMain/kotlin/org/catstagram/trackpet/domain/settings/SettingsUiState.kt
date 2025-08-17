package org.catstagram.trackpet.domain.settings

data class SettingsUiState(
    val user: User? = null,
    val isLoading: Boolean = false,
    val isSigningOut: Boolean = false,
    val language: Language = Language.ENGLISH,
    val isDarkTheme: Boolean = false,
    val areNotificationsEnabled: Boolean = false,
    val notificationPermissionState: NotificationPermissionState = NotificationPermissionState.NotRequested
)