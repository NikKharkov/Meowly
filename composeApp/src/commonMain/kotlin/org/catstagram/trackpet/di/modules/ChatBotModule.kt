package org.catstagram.trackpet.di.modules

import com.aallam.openai.api.model.ModelId
import org.catstagram.trackpet.presentation.screens.chatbot.ChatBotViewModel
import org.catstagram.trackpet.util.ImageSaver
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val chatBotModule = module {
    single(named("chat")) { ModelId("gpt-3.5-turbo") }
    single(named("image")) { ModelId("dall-e-2") }
    viewModel {
        ChatBotViewModel(
            apiKeyFetcher = get(),
            chatModel = get(named("chat")),
            imageModel = get(named("image")),
            settings = get(),
            imageSaver = get()
        )
    }
    singleOf(::ImageSaver)
}