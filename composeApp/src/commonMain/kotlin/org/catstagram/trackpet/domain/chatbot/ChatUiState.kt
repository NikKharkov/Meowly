package org.catstagram.trackpet.domain.chatbot

data class ChatUiState(
    val messages: List<ChatMessageItem> = emptyList(),
    val isGenerating: Boolean = false,
    val currentMessage: ChatMessageItem? = null,
    val error: String? = null,
    val saveImageResult: SaveImageResult = SaveImageResult.IDLE
)
