package com.styletheory.cariaku.di

import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single { createDataStore(androidContext()) }
    single { DataStoreRepository(get()) }
}