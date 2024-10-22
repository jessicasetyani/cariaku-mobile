package com.styletheory.cariaku.android.ui.screen.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import com.styletheory.cariaku.data.model.request.LoginUserRequest
import com.styletheory.cariaku.data.model.request.ParameterDataRequest
import com.styletheory.cariaku.data.model.response.UserResponse
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.remote.createHttpClient
import com.styletheory.cariaku.util.NetworkError
import com.styletheory.cariaku.util.onError
import com.styletheory.cariaku.util.onSuccess
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AuthScreen(onAuthenticated: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var savedUserId: String = remember { "" }
    val dataStoreRepository = remember {
        DataStoreRepository(dataStore = createDataStore(context = context))
    }

    LaunchedEffect(Unit) {
        dataStoreRepository.getUserId().collectLatest {
            savedUserId = it
        }
    }

    val client = remember {
        BackForAppClient(createHttpClient(OkHttp.create()))
    }
    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }

    val viewModel = viewModel<AuthViewModel>()
    val myUsername by viewModel.myUsername
    val myPassword by viewModel.myPassword


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = myUsername,
            placeholder = { Text(text = "Username") },
            onValueChange = { viewModel.setUsername(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            )
        )
        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = myPassword,
            placeholder = { Text(text = "Password") },
            onValueChange = { viewModel.setPassword(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                scope.launch {
                    errorMessage = null
                    client.loginUser(LoginUserRequest(username = myUsername, password = myPassword))
                        .onSuccess { response ->
                            scope.launch {
                                dataStoreRepository.saveUserId(response.objectId)
                                val sessionToken = response.sessionToken
                                dataStoreRepository.saveSessionToken(sessionToken)
                                val currentUser: UserResponse = client.getCurrentUser(sessionToken)
                                currentUser.userProfile?.objectId?.let { objectId ->
                                    val userProfile = client.getUserProfile(ParameterDataRequest(objectId))
                                    if (userProfile != null) {
                                        onAuthenticated()
                                    } else {
                                        Toast.makeText(context, "User profile not found", Toast.LENGTH_LONG).show()
                                    }
                                }
                            }
                        }
                        .onError {
                            errorMessage = it
                            println("Back4App error $it")
                            Toast.makeText(context, it.name, Toast.LENGTH_LONG).show()
                        }
                }
//                viewModel.onSignInClick(
//                    onSuccess = { userId ->
//                        onAuthenticated()
//                        scope.launch {
//                            dataStoreRepository.saveUserId(userId)
//                        }
//                    },
//                    onError = {
//                        Toast.makeText(context, it, Toast.LENGTH_LONG).show()
//                    }
//                )
            }
        ) {
            Text(text = "Sign in")
        }
    }
}