package com.styletheory.cariaku.android.ui.screen.home.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.model.Assistant
import com.styletheory.cariaku.data.remote.BackForAppClient
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val backForAppClient: BackForAppClient
) : ViewModel() {

    private val _userName: MutableState<String> = mutableStateOf("")
    val userName: State<String> = _userName

    private val _topPopularAssistant: MutableState<List<Assistant>> = mutableStateOf(emptyList())
    val topPopularAssistant: State<List<Assistant>> = _topPopularAssistant

    init {
        fetchTopPopularAssistants()
        fetchUserProfile()
    }

    private fun fetchTopPopularAssistants() {
        viewModelScope.launch {
            val response = backForAppClient.getTopFavoriteAssistant()
            _topPopularAssistant.value = response.results.take(4)
        }
    }

    private fun fetchUserProfile() {
        dataStoreRepository.getUserProfile()
            .onEach { userProfile ->
                _userName.value = userProfile?.name ?: ""
            }
            .launchIn(viewModelScope)
    }
}
