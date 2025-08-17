package org.catstagram.trackpet.presentation.screens.chatbot

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.api.image.ImageCreation
import com.aallam.openai.api.image.ImageSize
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.aallam.openai.client.RetryStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.catstagram.trackpet.analytics.Analytics
import org.catstagram.trackpet.data.remote.ApiKeyFetcher
import org.catstagram.trackpet.domain.chatbot.ChatMessageItem
import org.catstagram.trackpet.domain.chatbot.ChatUiState
import org.catstagram.trackpet.domain.chatbot.MessageType
import org.catstagram.trackpet.domain.chatbot.SaveImageResult
import org.catstagram.trackpet.managers.settings.SettingsManager
import org.catstagram.trackpet.util.CHAT_IMAGE_REQUEST
import org.catstagram.trackpet.util.CHAT_SAVE_IMAGE
import org.catstagram.trackpet.util.CHAT_TEXT_REQUEST
import org.catstagram.trackpet.util.ImageSaver
import org.catstagram.trackpet.util.SYSTEM_PROMPT
import kotlin.time.Duration.Companion.seconds

class ChatBotViewModel(
    private val apiKeyFetcher: ApiKeyFetcher,
    private val chatModel: ModelId,
    private val imageModel: ModelId,
    private val settings: SettingsManager,
    private val imageSaver: ImageSaver
) : ViewModel() {

    private val _chatUiState = MutableStateFlow(ChatUiState())
    val chatUiState = _chatUiState.asStateFlow()

    private lateinit var openAI: OpenAI

    init {
        viewModelScope.launch {
            loadChatHistory()
            openAI = getOpenAiClient()
        }
    }

    private suspend fun getOpenAiClient(): OpenAI {
        val apiKeyResult = apiKeyFetcher.getApiKey()
        val apiKey = apiKeyResult.getOrNull()

        val openAI = OpenAI(
            token = apiKey ?: "",
            timeout = Timeout(socket = 60.seconds, request = 90.seconds),
            retry = RetryStrategy(maxRetries = 3)
        )

        return openAI
    }

    fun sendMessage(message: String) {
        addMessage(ChatMessageItem.userText(message))

        processImageRequest(message)?.let { prompt ->
            generateImage(prompt)
            Analytics.trackFeatureUsed(CHAT_IMAGE_REQUEST)
        } ?: run {
            sendTextMessage(message)
            Analytics.trackFeatureUsed(CHAT_TEXT_REQUEST)
        }
    }

    private fun sendTextMessage(message: String) {
        viewModelScope.launch {
            try {
                _chatUiState.value = _chatUiState.value.copy(isGenerating = true)

                val request = ChatCompletionRequest(
                    model = chatModel,
                    messages = buildChatHistory() + ChatMessage(ChatRole.User, message),
                    temperature = 0.7
                )

                handleStreamingResponse(isStart = true)
                var fullContent = ""

                openAI.chatCompletions(request).collect { chunk ->
                    chunk.choices.firstOrNull()?.delta?.content?.let { delta ->
                        fullContent += delta
                        handleStreamingResponse(fullContent)
                    }
                }

                handleStreamingResponse(fullContent, isEnd = true)

            } catch (e: Exception) {
                handleStreamingResponse("Sorry, my paws got tangled 🐾 Try again!\n", isEnd = true)
                _chatUiState.value = _chatUiState.value.copy(error = e.message)
            } finally {
                _chatUiState.value = _chatUiState.value.copy(isGenerating = false)
            }
        }
    }

    private fun generateImage(prompt: String) {
        viewModelScope.launch {
            try {
                _chatUiState.value = _chatUiState.value.copy(isGenerating = true)

                val request = ImageCreation(
                    prompt = prompt,
                    model = imageModel,
                    size = ImageSize.is512x512
                )

                val response = openAI.imageURL(request)
                val imageUrl = response.first().url
                addMessage(ChatMessageItem.botImage(imageUrl, prompt))

            } catch (e: Exception) {
                addMessage(ChatMessageItem.botText("I can't draw 😿 My paws are shaking!"))
                _chatUiState.value = _chatUiState.value.copy(error = e.message)
            } finally {
                _chatUiState.value = _chatUiState.value.copy(isGenerating = false)
            }
        }
    }

    private fun handleStreamingResponse(
        content: String = "",
        isStart: Boolean = false,
        isEnd: Boolean = false
    ) {
        when {
            isStart -> _chatUiState.value = _chatUiState.value.copy(
                currentMessage = ChatMessageItem.botText("").copy(isStreaming = true)
            )

            isEnd -> {
                addMessage(ChatMessageItem.botText(content))
                _chatUiState.value = _chatUiState.value.copy(currentMessage = null)
            }

            else -> _chatUiState.value.currentMessage?.let { streamingMsg ->
                _chatUiState.value = _chatUiState.value.copy(
                    currentMessage = streamingMsg.copy(content = content)
                )
            }
        }
    }

    private fun processImageRequest(message: String): String? {
        val imageKeywords = listOf(
            "нарисуй", "картинку", "изображение", "фото", "покажи",
            "draw", "image", "picture", "photo", "show me", "generate"
        )

        return if (imageKeywords.any { message.lowercase().contains(it) }) {
            message.replace(
                Regex(
                    "(нарисуй|покажи|картинку|draw|show me|generate)\\s*",
                    RegexOption.IGNORE_CASE
                ), ""
            ).trim()
        } else null
    }

    private fun buildChatHistory(): List<ChatMessage> {
        val systemMessage = ChatMessage(ChatRole.System, SYSTEM_PROMPT)
        val history = _chatUiState.value.messages
            .filter { it.type == MessageType.TEXT }
            .map { message ->
                ChatMessage(
                    role = if (message.isUserMessage) ChatRole.User else ChatRole.Assistant,
                    content = message.content
                )
            }
        return listOf(systemMessage) + history
    }

    private fun addMessage(message: ChatMessageItem) {
        val newMessages = (_chatUiState.value.messages + message).takeLast(20)
        _chatUiState.value = _chatUiState.value.copy(messages = newMessages)
        settings.chatHistory = newMessages
    }

    fun clearChat() {
        _chatUiState.value = _chatUiState.value.copy(
            messages = emptyList(),
            currentMessage = null
        )
        settings.clearChatHistory()
    }

    private fun loadChatHistory() {
        val savedMessages = settings.chatHistory
        _chatUiState.value = _chatUiState.value.copy(messages = savedMessages)
    }

    fun saveImageToGallery(imageUrl: String) {
        viewModelScope.launch {
            try {
                _chatUiState.value =
                    _chatUiState.value.copy(saveImageResult = SaveImageResult.SAVING)
                imageSaver.saveImageToGallery(imageUrl)
                _chatUiState.value =
                    _chatUiState.value.copy(saveImageResult = SaveImageResult.SUCCESS)
                Analytics.trackFeatureUsed(CHAT_SAVE_IMAGE)
            } catch (e: Exception) {
                println("Error while trying to save image ViewModel: $e")
                _chatUiState.value =
                    _chatUiState.value.copy(saveImageResult = SaveImageResult.ERROR)
            }
        }
    }

    fun clearSaveImageState() {
        _chatUiState.value = _chatUiState.value.copy(saveImageResult = SaveImageResult.IDLE)
    }
}