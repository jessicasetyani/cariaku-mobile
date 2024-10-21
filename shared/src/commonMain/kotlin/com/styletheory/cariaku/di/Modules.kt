package com.styletheory.cariaku.di

import com.styletheory.cariaku.data.repository.BackForAppRespository
import com.styletheory.cariaku.data.repository.BackForAppRespositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<BackForAppRespository> { BackForAppRespositoryImpl() }
//    viewModel { AssistantAndalanViewModel(get()) }
}