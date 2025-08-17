package org.catstagram.trackpet.di.modules

import org.catstagram.trackpet.presentation.screens.handbook.HandbookViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val handbookModule = module {
    viewModelOf(::HandbookViewModel)
}