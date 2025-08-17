package org.catstagram.trackpet.presentation.screens.policy

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import catstagrammp.composeapp.generated.resources.Res
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.WebViewFileReadType
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLFile
import org.catstagram.trackpet.domain.settings.Language
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel
import org.catstagram.trackpet.presentation.shared.TopBar

@Composable
fun PrivacyPolicyScreen(
    onBackClick: () -> Unit,
    settingsViewModel: SettingsViewModel
) {
    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()
    val fileName =
        Res.getUri(if (settingsUiState.language == Language.ENGLISH) "files/privacy_en.html" else "files/privacy_ru.html")
    val webViewState = rememberWebViewStateWithHTMLFile(
        fileName = fileName,
        readType = WebViewFileReadType.COMPOSE_RESOURCE_FILES
    )

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            userAvatarURL = settingsUiState.user?.profilePicture,
            onBackClick = onBackClick,
            toSettingsScreen = {}
        )

        WebView(
            state = webViewState,
            captureBackPresses = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}