package org.catstagram.trackpet.di.modules

import org.catstagram.trackpet.data.local.AndroidDatabaseDriverFactory
import org.catstagram.trackpet.data.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<DatabaseDriverFactory> { AndroidDatabaseDriverFactory(androidContext()) }
}