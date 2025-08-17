package org.catstagram.trackpet.di.modules

import org.catstagram.trackpet.presentation.screens.add_edit.CatViewModel
import org.catstagram.trackpet.data.local.Database
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val databaseModule = module {
    singleOf(::Database)
    viewModelOf(::CatViewModel)
}