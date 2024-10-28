package com.styletheory.cariaku.android.ui.screen.auth

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.model.request.LoginUserRequest
import com.styletheory.cariaku.data.model.request.ParameterDataRequest
import com.styletheory.cariaku.data.model.response.UserResponse
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.util.NetworkError
import com.styletheory.cariaku.util.onError
import com.styletheory.cariaku.util.onSuccess
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AuthViewModel(private val dataStoreRepository: DataStoreRepository, private val backForAppClient: BackForAppClient) : ViewModel() {

    private var _myUsername = mutableStateOf("")
    val myUsername: State<String> = _myUsername

    private var _myPassword = mutableStateOf("")
    val myPassword: State<String> = _myPassword

    private val _errorMessage = MutableStateFlow<NetworkError?>(null)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun setUsername(text: String) {
        _myUsername.value = text
    }

    fun setPassword(text: String) {
        _myPassword.value = text
    }

    fun onSignInClick(
        onAuthenticated: () -> Unit,
        onError: (String) -> Unit
    ) {
        if(myUsername.value.isNotEmpty() && myPassword.value.isNotEmpty()) {
            loginTheUser(onAuthenticated, onError)
        } else {
            onError("Fields are empty.")
        }
    }

    private fun loginTheUser(
        onAuthenticated: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = backForAppClient.loginUser(LoginUserRequest(username = myUsername.value, password = myPassword.value))
                response.onSuccess { loginResponse ->
                    dataStoreRepository.saveUserId(loginResponse.objectId)
                    val sessionToken = loginResponse.sessionToken
                    val currentUser: UserResponse = backForAppClient.getCurrentUser(sessionToken)
                    dataStoreRepository.saveSessionToken(sessionToken)
                    currentUser.userProfile?.objectId?.let { objectId ->
                        val userProfile = backForAppClient.getUserProfile(ParameterDataRequest(objectId))
                        userProfile?.let {
                            dataStoreRepository.saveUserProfile(it)
                            onAuthenticated()
                        } ?: onError("Failed to fetch user profile.")
                    }
                }.onError { errorMessage ->
                    _errorMessage.update { errorMessage }
                    onError(errorMessage.toUserFriendlyMessage())
                }
            } catch(e: Exception) {
                onError("An error occurred: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

}
