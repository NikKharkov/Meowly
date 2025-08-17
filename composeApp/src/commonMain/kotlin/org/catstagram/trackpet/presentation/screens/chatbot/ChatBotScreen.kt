package org.catstagram.trackpet.presentation.screens.chatbot

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.catstagram.trackpet.domain.chatbot.ChatMessageItem
import org.catstagram.trackpet.domain.chatbot.ChatUiState
import org.catstagram.trackpet.domain.chatbot.SaveImageResult
import org.catstagram.trackpet.presentation.screens.chatbot.components.ChatBotTopBar
import org.catstagram.trackpet.presentation.screens.chatbot.components.ChatInputField
import org.catstagram.trackpet.presentation.screens.chatbot.components.GeneratingIndicator
import org.catstagram.trackpet.presentation.screens.chatbot.components.MessageBubble
import org.catstagram.trackpet.presentation.screens.chatbot.components.NewChatDialog
import org.catstagram.trackpet.presentation.theme.LocalAppColors
import org.catstagram.trackpet.presentation.theme.LocalAppDimensions
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChatBotScreen(
    onBackClick: () -> Unit,
    onInfoClick: () -> Unit,
    chatBotViewModel: ChatBotViewModel = koinViewModel()
) {
    val chatBotUiState by chatBotViewModel.chatUiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var showNewChatDialog by remember { mutableStateOf(false) }

    LaunchedEffect(chatBotUiState.saveImageResult) {
        when (chatBotUiState.saveImageResult) {
            SaveImageResult.SUCCESS -> {
                snackbarHostState.showSnackbar("✅")
                chatBotViewModel.clearSaveImageState()
            }

            SaveImageResult.ERROR -> {
                snackbarHostState.showSnackbar("❌")
                chatBotViewModel.clearSaveImageState()
            }

            else -> {}
        }
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val isTablet = maxWidth > 600.dp
        val colors = LocalAppColors.current
        val maxWidth = if (isTablet) 600.dp else Dp.Unspecified

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(colors.background),
            contentAlignment = Alignment.Center
        ) {
            Scaffold(
                modifier = Modifier
                    .widthIn(max = maxWidth)
                    .fillMaxHeight()
                    .windowInsetsPadding(WindowInsets.systemBars),
                topBar = {
                    ChatBotTopBar(
                        onBackClick = onBackClick,
                        onInfoClick = onInfoClick,
                        onNewChatClick = { showNewChatDialog = true }
                    )
                },
                bottomBar = {
                    ChatInputField(
                        onSendMessage = { message ->
                            chatBotViewModel.sendMessage(message)
                        }
                    )
                },
                snackbarHost = {
                    SnackbarHost(snackbarHostState)
                },
                containerColor = colors.background
            ) { paddingValues ->
                ChatBotComponents(
                    modifier = Modifier.padding(paddingValues),
                    chatUiState = chatBotUiState,
                    isTablet = isTablet,
                    saveImage = { url ->
                        chatBotViewModel.saveImageToGallery(url)
                    }
                )
            }
        }
    }

    if (showNewChatDialog) {
        NewChatDialog(
            onConfirm = {
                chatBotViewModel.clearChat()
                showNewChatDialog = false
            },
            onDismiss = {
                showNewChatDialog = false
            }
        )
    }
}

@Composable
private fun ChatBotComponents(
    modifier: Modifier = Modifier,
    chatUiState: ChatUiState,
    isTablet: Boolean,
    saveImage: (String) -> Unit
) {
    val dimensions = LocalAppDimensions.current
    val contentPadding = if (isTablet) dimensions.paddingXLarge else dimensions.paddingMedium
    val listState = rememberLazyListState()

    LaunchedEffect(chatUiState.messages.size, chatUiState.currentMessage?.content) {
        if (chatUiState.messages.isNotEmpty() || chatUiState.currentMessage != null) {
            listState.animateScrollToItem(
                index = chatUiState.messages.size +
                        (if (chatUiState.currentMessage != null) 1 else 0) +
                        (if (chatUiState.isGenerating && chatUiState.currentMessage == null) 1 else 0)
            )
        }
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(contentPadding),
        state = listState,
        verticalArrangement = Arrangement.spacedBy(dimensions.paddingMedium)
    ) {
        items(items = chatUiState.messages, key = { it.id }) { message ->
            MessageBubble(
                messageItem = message,
                isTablet = isTablet,
                saveImage = saveImage,
                isSaving = chatUiState.saveImageResult == SaveImageResult.SAVING
            )
        }

        chatUiState.currentMessage?.let { streamingMsg ->
            item {
                MessageBubble(
                    messageItem = streamingMsg,
                    isTablet = isTablet,
                    saveImage = saveImage,
                    isSaving = chatUiState.saveImageResult == SaveImageResult.SAVING
                )
            }
        }

        if (chatUiState.isGenerating && chatUiState.currentMessage == null) {
            item {
                GeneratingIndicator()
            }
        }
    }
}

@Preview
@Composable
private fun ChatBotScreenPreview() {
    val testMessages = listOf(
        ChatMessageItem.userText("Привет! Нарисуй мне котика"),
        ChatMessageItem.botText("Конечно! Сейчас нарисую тебе милого котика 🐱"),
        ChatMessageItem.botImage(
            imageUrl = "https://example.com/cat.jpg",
            prompt = "милый котик"
        ),
        ChatMessageItem.userText("Классный котик! А теперь собачку"),
        ChatMessageItem.botText("Отлично! Рисую собачку...")
    )

    val testState = ChatUiState(
        messages = testMessages,
        isGenerating = false,
        currentMessage = null,
        saveImageResult = SaveImageResult.IDLE
    )

    ChatBotComponents(
        modifier = Modifier,
        chatUiState = testState,
        isTablet = false,
        saveImage = {}
    )
}