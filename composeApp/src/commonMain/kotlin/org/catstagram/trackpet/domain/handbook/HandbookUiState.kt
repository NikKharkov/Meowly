package org.catstagram.trackpet.domain.handbook

data class HandbookUiState(
    val cats: List<CatBreed> = emptyList(),
    val savedCats: List<CatBreed> = emptyList(),
    val filteredCats: List<CatBreed> = emptyList(),
    val searchQuery: String = "",
    val showOnlySaved: Boolean = false
)