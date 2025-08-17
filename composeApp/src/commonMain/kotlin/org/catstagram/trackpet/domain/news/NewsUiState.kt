package org.catstagram.trackpet.domain.news

import androidx.compose.runtime.Immutable

@Immutable
data class NewsUiState(
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val currentLanguage: String = "en"
)
