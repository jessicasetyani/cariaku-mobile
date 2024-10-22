package com.styletheory.cariaku.android.ui.screen.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.remote.BackForAppClient

class AuthViewModelFactory(
    private val dataStoreRepository: DataStoreRepository,
    private val backForAppClient: BackForAppClient
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AuthViewModel(dataStoreRepository, backForAppClient) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
