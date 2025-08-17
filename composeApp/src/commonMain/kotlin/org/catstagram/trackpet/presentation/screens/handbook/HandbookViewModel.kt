package org.catstagram.trackpet.presentation.screens.handbook

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.catstagram.trackpet.data.local.CatBreeds
import org.catstagram.trackpet.domain.handbook.CatBreed
import org.catstagram.trackpet.domain.handbook.HandbookUiState
import org.catstagram.trackpet.domain.handbook.TranslatedCatData
import org.catstagram.trackpet.managers.settings.SettingsManager

class HandbookViewModel(private val settingsManager: SettingsManager) : ViewModel() {

    private val _handbookUiState = MutableStateFlow(
        HandbookUiState(
            cats = CatBreeds.breeds,
            filteredCats = CatBreeds.breeds,
            savedCats = getSavedCats(),
            searchQuery = "",
            showOnlySaved = false
        )
    )
    val handbookUiState = _handbookUiState.asStateFlow()

    private fun getSavedCats(): List<CatBreed> {
        val savedIds = settingsManager.savedCatBreeds
        return CatBreeds.breeds.filter { it.id in savedIds }
    }

    fun toggleBookmark(catId: Int) {
        val savedCatBreeds = settingsManager.savedCatBreeds.toMutableSet()
        if (catId in savedCatBreeds) {
            savedCatBreeds.remove(catId)
        } else {
            savedCatBreeds.add(catId)
        }
        settingsManager.savedCatBreeds = savedCatBreeds

        _handbookUiState.value = _handbookUiState.value.copy(
            savedCats = getSavedCats()
        )
    }

    fun performSearch(translatedTexts: Map<Int, TranslatedCatData>) {
        val query = _handbookUiState.value.searchQuery.lowercase().trim()
        val showOnlySaved = _handbookUiState.value.showOnlySaved
        val savedIds = settingsManager.savedCatBreeds

        val filtered = CatBreeds.breeds.filter { breed ->
            val matchesSavedFilter = if (showOnlySaved) breed.id in savedIds else true

            if (!matchesSavedFilter) return@filter false

            if (query.isEmpty()) return@filter true

            val translatedData = translatedTexts[breed.id] ?: return@filter false

            listOf(
                translatedData.name,
                translatedData.origin,
                translatedData.size,
                translatedData.coat
            ).any { it.lowercase().contains(query) } ||
                    translatedData.personality.any { it.lowercase().contains(query) }
        }

        _handbookUiState.value = _handbookUiState.value.copy(filteredCats = filtered)
    }

    fun updateSearchQuery(query: String) {
        _handbookUiState.value = _handbookUiState.value.copy(searchQuery = query)
    }

    fun toggleSavedFilter() {
        val currentState = _handbookUiState.value
        _handbookUiState.value = currentState.copy(showOnlySaved = !currentState.showOnlySaved)
    }
}