package org.catstagram.trackpet.presentation.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewStateWithHTMLData
import org.catstagram.trackpet.presentation.shared.TopBar
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NewsDetailScreen(
    newsId: Int,
    onBackClick: () -> Unit,
    settingsViewModel: SettingsViewModel,
    toSettingsScreen: () -> Unit,
    newsViewModel: NewsViewModel = koinViewModel()
) {
    val newsUiState by newsViewModel.newsUiState.collectAsState()
    val selectedNews = newsUiState.news.find { it.id == newsId }

    val webViewState = rememberWebViewStateWithHTMLData(data = selectedNews?.content ?: "")
    val settingsUiState by settingsViewModel.settingsUiState.collectAsState()
    webViewState.webSettings.apply {
        isJavaScriptEnabled = true
        androidWebSettings.domStorageEnabled = true
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(
            userAvatarURL = settingsUiState.user?.profilePicture,
            onBackClick = onBackClick,
            toSettingsScreen = toSettingsScreen
        )

        WebView(
            state = webViewState,
            captureBackPresses = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}