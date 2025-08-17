package org.catstagram.trackpet.domain.news

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: Int,
    val title: String,
    val content: String,
    val imageUrl: String,
    val createdAt: String
)
