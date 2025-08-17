package org.catstagram.trackpet.di.modules

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.messaging.messaging
import dev.icerock.moko.permissions.PermissionsController
import org.catstagram.trackpet.managers.auth.AuthenticationManager
import org.catstagram.trackpet.managers.settings.SettingsManager
import org.catstagram.trackpet.presentation.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authenticationModule = module {
    single { Firebase.auth }
    single { Firebase.messaging }
    singleOf(::AuthenticationManager)
    singleOf(::SettingsManager)
    viewModel { (permissionsController: PermissionsController) ->
        SettingsViewModel(
            authManager = get(),
            settingsManager = get(),
            fcm = get(),
            permissionsController = permissionsController
        )
    }
}