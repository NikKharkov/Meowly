package org.catstagram.trackpet.domain.settings

data class User(
    val id: String,
    val name: String? = null,
    val profilePicture: String?
)
