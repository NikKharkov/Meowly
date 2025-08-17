package org.catstagram.trackpet.di.modules

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.storage.storage
import org.catstagram.trackpet.data.gallery.FirebaseStorageService
import org.catstagram.trackpet.presentation.screens.gallery.GalleryViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val galleryModule = module {
    single { Firebase.storage.reference.child("images") }
    singleOf(::FirebaseStorageService)
    viewModelOf(::GalleryViewModel)
}