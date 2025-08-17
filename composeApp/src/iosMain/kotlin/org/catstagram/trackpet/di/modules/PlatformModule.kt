package org.catstagram.trackpet.di.modules

import org.catstagram.trackpet.data.local.DatabaseDriverFactory
import org.catstagram.trackpet.data.local.IOSDatabaseDriverFactory
import org.koin.dsl.module

actual val platformModule = module {
    single<DatabaseDriverFactory> { IOSDatabaseDriverFactory() }
}