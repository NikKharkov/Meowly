package org.catstagram.trackpet.domain.handbook

data class TranslatedCatData(
    val name: String,
    val origin: String,
    val size: String,
    val coat: String,
    val personality: List<String>
)
