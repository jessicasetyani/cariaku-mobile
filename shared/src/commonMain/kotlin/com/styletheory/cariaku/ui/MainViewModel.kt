package com.styletheory.cariaku.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.model.UserProfile
import com.styletheory.cariaku.data.model.request.ParameterDataRequest
import com.styletheory.cariaku.data.remote.BackForAppClient
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class MainViewModel(
    private val dataStoreRepository: DataStoreRepository,
    private val backForAppClient: BackForAppClient
) : ViewModel() {

    private var _userId = mutableStateOf("")
    val userId: State<String> = _userId

    private var _userProfile = mutableStateOf<UserProfile?>(null)
    val userProfile: State<UserProfile?> = _userProfile

    private var _isInitialized = mutableStateOf(false)
    val isInitialized: State<Boolean> = _isInitialized

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        viewModelScope.launch {
            dataStoreRepository.getUserId().collectLatest { userIdFromDataStore ->
                _userId.value = userIdFromDataStore
                if(userIdFromDataStore.isNotEmpty()) {
                    dataStoreRepository.getUserProfile().collectLatest { profileFromDataStore ->
                        _userProfile.value = profileFromDataStore
                        if(profileFromDataStore == null) {
                            val sessionToken = dataStoreRepository.getSessionToken().firstOrNull()
                            if(sessionToken != null) {
                                try {
                                    val userResponse = backForAppClient.getCurrentUser(sessionToken = sessionToken)
                                    if(userResponse.objectId != null) {
                                        val fetchedUserProfile = backForAppClient.getUserProfile(
                                            ParameterDataRequest(objectId = userResponse.objectId)
                                        )
                                        if(fetchedUserProfile != null) {
                                            dataStoreRepository.saveUserProfile(fetchedUserProfile)
                                            _userProfile.value = fetchedUserProfile
                                        }
                                    }
                                } catch(e: Exception) {
                                    // Handle API call error
                                    e.printStackTrace()
                                }
                            }
                        }
                        _isInitialized.value = true
                    }
                } else {
                    _isInitialized.value = true
                }
            }
        }
    }
}
