package com.styletheory.cariaku.di

import com.styletheory.cariaku.data.repository.RegisterUserRepository
import com.styletheory.cariaku.data.repository.RegisterUserRepositoryImpl
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single<RegisterUserRepository> { RegisterUserRepositoryImpl() }
//    viewModel { AssistantAndalanViewModel(get()) }
}