package org.catstagram.trackpet.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Add : Screen()

    @Serializable
    data class Edit(val catId: Long) : Screen()

    @Serializable
    data object Home : Screen()

    @Serializable
    data class Gallery(val catId: Long) : Screen()

    @Serializable
    data object ChatBot : Screen()

    @Serializable
    data object ChatBotInfo : Screen()

    @Serializable
    data object Settings : Screen()

    @Serializable
    data object Handbook : Screen()

    @Serializable
    data class HandbookDetails(val catBreedId: Int) : Screen()

    @Serializable
    data object News : Screen()

    @Serializable
    data class NewsDetail(val newsId: Int) : Screen()

    @Serializable
    data object Privacy : Screen()
}