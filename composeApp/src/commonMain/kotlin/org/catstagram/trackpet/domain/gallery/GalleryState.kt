package org.catstagram.trackpet.domain.gallery

data class GalleryState<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isNotAuthenticated: Boolean = false
) {
    companion object {
        fun <T> loading() = GalleryState<T>(isLoading = true)
        fun <T> success(data: T) = GalleryState(data = data)
        fun <T> error(message: String) = GalleryState<T>(error = message)
        fun <T> notAuthenticated() = GalleryState<T>(isNotAuthenticated = true)
    }
}
