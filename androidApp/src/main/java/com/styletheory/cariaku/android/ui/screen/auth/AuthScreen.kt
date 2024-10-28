package com.styletheory.cariaku.android.ui.screen.auth

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.remote.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.collectLatest

@Composable
fun AuthScreen(onAuthenticated: () -> Unit) {
    val context = LocalContext.current
    val dataStoreRepository = remember {
        DataStoreRepository(dataStore = createDataStore(context = context))
    }
    val backForAppClient = remember {
        BackForAppClient(createHttpClient(OkHttp.create()))
    }
    val viewModel = viewModel<AuthViewModel>(factory = AuthViewModelFactory(dataStoreRepository, backForAppClient))
    val myUsername by viewModel.myUsername
    val myPassword by viewModel.myPassword
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        dataStoreRepository.getUserProfile().collectLatest { userProfile ->
            if(userProfile != null) {
                onAuthenticated()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center
    ) {
        Column(
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
                    viewModel.onSignInClick(
                        onAuthenticated = onAuthenticated,
                        onError = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            ) {
                Text(text = "Sign in")
            }
        }

        if(isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}
