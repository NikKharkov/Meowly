package org.catstagram.trackpet.di

import org.catstagram.trackpet.di.modules.authenticationModule
import org.catstagram.trackpet.di.modules.chatBotModule
import org.catstagram.trackpet.di.modules.databaseModule
import org.catstagram.trackpet.di.modules.galleryModule
import org.catstagram.trackpet.di.modules.handbookModule
import org.catstagram.trackpet.di.modules.networkModule
import org.catstagram.trackpet.di.modules.platformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            databaseModule, platformModule, galleryModule,
            authenticationModule, networkModule, chatBotModule, handbookModule
        )
    }
}