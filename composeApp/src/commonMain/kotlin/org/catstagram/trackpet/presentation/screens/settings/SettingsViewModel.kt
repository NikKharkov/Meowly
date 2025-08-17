package org.catstagram.trackpet.presentation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.messaging.FirebaseMessaging
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.notifications.REMOTE_NOTIFICATION
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.domain.settings.Language
import org.catstagram.trackpet.domain.settings.SettingsUiState
import org.catstagram.trackpet.managers.auth.AuthenticationManager
import org.catstagram.trackpet.managers.settings.SettingsManager
import org.catstagram.trackpet.util.NOTIFICATIONS_TOPIC
import org.catstagram.trackpet.util.SETTINGS_LANGUAGE
import org.catstagram.trackpet.util.SETTINGS_NOTIFICATIONS_DISABLED
import org.catstagram.trackpet.util.SETTINGS_NOTIFICATIONS_ENABLED
import org.catstagram.trackpet.util.SETTINGS_THEME
import org.catstagram.trackpet.util.SETTINGS_USER_AUTHENTICATED

class SettingsViewModel(
    private val authManager: AuthenticationManager,
    private val settingsManager: SettingsManager,
    private val fcm: FirebaseMessaging,
    private val permissionsController: PermissionsController
) : ViewModel() {

    private val _settingsUiState = MutableStateFlow(SettingsUiState())
    val settingsUiState: StateFlow<SettingsUiState> = _settingsUiState.asStateFlow()

    init {
        loadSettings()
    }

    fun loadSettings() {
        viewModelScope.launch {
            val user = authManager.getCurrentUser()
            val notificationsEnabled = settingsManager.areNotificationsEnabled && checkNotificationPermission()

            _settingsUiState.value = SettingsUiState(
                user = user,
                isDarkTheme = settingsManager.isDarkTheme,
                areNotificationsEnabled = notificationsEnabled,
                language = settingsManager.language
            )
        }
    }

    private suspend fun checkNotificationPermission(): Boolean {
        return try {
            permissionsController.isPermissionGranted(Permission.REMOTE_NOTIFICATION)
        } catch (e: Exception) {
            false
        }
    }

    fun handleAuthResult(result: Result<FirebaseUser?>) {
        viewModelScope.launch {
            _settingsUiState.value = _settingsUiState.value.copy(isLoading = true)
            try {
                val user = authManager.handleAuthResult(result)
                _settingsUiState.value = _settingsUiState.value.copy(user = user, isLoading = false)
                Analytics.trackFeatureUsed(SETTINGS_USER_AUTHENTICATED)
            } catch (e: Exception) {
                _settingsUiState.value = _settingsUiState.value.copy(isLoading = false)
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            _settingsUiState.value = _settingsUiState.value.copy(isSigningOut = true)
            try {
                authManager.signOut()
                _settingsUiState.value = _settingsUiState.value.copy(user = null, isSigningOut = false)
            } catch (e: Exception) {
                _settingsUiState.value = _settingsUiState.value.copy(isSigningOut = false)
            }
        }
    }

    fun changeTheme(isDarkTheme: Boolean) {
        _settingsUiState.value = _settingsUiState.value.copy(isDarkTheme = isDarkTheme)
        settingsManager.isDarkTheme = isDarkTheme
        Analytics.trackFeatureUsed(SETTINGS_THEME)
    }

    fun changeLanguage(language: Language) {
        _settingsUiState.value = _settingsUiState.value.copy(language = language)
        settingsManager.language = language
        Analytics.trackFeatureUsed(SETTINGS_LANGUAGE)
    }

    fun toggleNotifications(
        enabled: Boolean,
        onPermissionDeniedForever: () -> Unit = {}
    ) {
        viewModelScope.launch {
            if (!enabled) {
                disableNotifications()
                return@launch
            }

            try {
                if (!checkNotificationPermission()) {
                    permissionsController.providePermission(Permission.REMOTE_NOTIFICATION)
                }
                enableNotifications()
            } catch (e: DeniedAlwaysException) {
                onPermissionDeniedForever()
            } catch (e: Exception) {
                println("Notification error: $e")
            }
        }
    }

    private fun enableNotifications() {
        fcm.subscribeToTopic(NOTIFICATIONS_TOPIC)
        settingsManager.areNotificationsEnabled = true
        _settingsUiState.value = _settingsUiState.value.copy(areNotificationsEnabled = true)
        Analytics.trackFeatureUsed(SETTINGS_NOTIFICATIONS_ENABLED)
    }

    private fun disableNotifications() {
        fcm.unsubscribeFromTopic(NOTIFICATIONS_TOPIC)
        settingsManager.areNotificationsEnabled = false
        _settingsUiState.value = _settingsUiState.value.copy(areNotificationsEnabled = false)
        Analytics.trackFeatureUsed(SETTINGS_NOTIFICATIONS_DISABLED)
    }

    fun openAppSettings() {
        permissionsController.openAppSettings()
    }
}