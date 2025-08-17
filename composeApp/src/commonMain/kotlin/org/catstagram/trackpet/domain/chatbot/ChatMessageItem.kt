package org.catstagram.trackpet.domain.chatbot

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class ChatMessageItem(
    val id: String = Uuid.random().toString(),
    val content: String = "",
    val imageUrl: String? = null,
    val type: MessageType,
    val isUserMessage: Boolean,
    val timestamp: Long = Clock.System.now().epochSeconds,
    val isStreaming: Boolean = false
) {
    companion object {
        fun userText(content: String) = ChatMessageItem(
            content = content,
            type = MessageType.TEXT,
            isUserMessage = true
        )

        fun botText(content: String) = ChatMessageItem(
            content = content,
            type = MessageType.TEXT,
            isUserMessage = false
        )

        fun botImage(imageUrl: String, prompt: String) = ChatMessageItem(
            content = prompt,
            imageUrl = imageUrl,
            type = MessageType.IMAGE,
            isUserMessage = false
        )
    }
}
