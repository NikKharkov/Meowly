package org.catstagram.trackpet.presentation.screens.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.data.remote.NewsApi
import org.catstagram.trackpet.domain.news.NewsUiState
import org.catstagram.trackpet.managers.settings.SettingsManager
import org.catstagram.trackpet.util.NEWS_ERROR

class NewsViewModel(
    private val newsApi: NewsApi,
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _newsUiState = MutableStateFlow(NewsUiState())
    val newsUiState = _newsUiState.asStateFlow()

    init {
        loadNews(settingsManager.language.code)
    }

    private fun loadNews(language: String) {
        viewModelScope.launch {
            _newsUiState.value = _newsUiState.value.copy(
                isLoading = true,
                currentLanguage = language
            )

            newsApi.getAllNews(language)
                .onSuccess { news ->
                    println("$TAG: ${news.size}")
                    _newsUiState.value = _newsUiState.value.copy(
                        news = news,
                        isLoading = false
                    )
                }
                .onFailure { exception ->
                    println("$TAG: ${exception.message}")
                    _newsUiState.value = _newsUiState.value.copy(
                        isLoading = false,
                        isError = true
                    )
                    Analytics.trackError(NEWS_ERROR)
                }
        }
    }

    companion object {
        const val TAG = "NewsViewModel"
    }
}