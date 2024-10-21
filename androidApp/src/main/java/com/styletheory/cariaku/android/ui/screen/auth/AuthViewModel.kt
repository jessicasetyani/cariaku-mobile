package com.styletheory.cariaku.android.ui.screen.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AuthViewModel() : ViewModel() {

    private var _myUsername: MutableState<String> = mutableStateOf("")
    val myUsername: State<String> = _myUsername

    private var _myPassword: MutableState<String> = mutableStateOf("")
    val myPassword: State<String> = _myPassword

    fun setUsername(text: String) {
        _myUsername.value = text
    }

    fun setPassword(text: String) {
        _myPassword.value = text
    }

    fun onSignInClick(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        if(myUsername.value.isNotEmpty() && myPassword.value.isNotEmpty()) {
            //check if the user already have an account or not
            //if yes, login the user
//                    loginTheUser(
//                        onSuccess = onSuccess,
//                        onError = onError
//                    )
        } else {
            onError("Fields are empty.")
        }
    }

    private fun loginTheUser(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        //login the user
    }
}