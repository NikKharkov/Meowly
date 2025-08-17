package org.catstagram.trackpet.domain.handbook

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

data class CatBreed(
    val id: Int,
    val name: StringResource,
    val shortDescription: StringResource,
    val origin: StringResource,
    val size: StringResource,
    val coat: StringResource,
    val personality: List<StringResource>,
    val careTips: StringResource,
    val funFact: StringResource,
    val image: DrawableResource
)
