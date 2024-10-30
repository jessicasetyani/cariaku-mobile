package com.styletheory.cariaku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.styletheory.cariaku.android.navigation.Screen
import com.styletheory.cariaku.android.navigation.SetupNavGraph
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.ui.MainViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.compose.KoinContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppContent()
        }
    }
}

@Composable
private fun AppContent() {
    MyApplicationTheme {
        KoinContext {
            val context = LocalContext.current
            val dataStoreRepository = remember {
                DataStoreRepository(dataStore = createDataStore(context = context))
            }
            val httpClient = HttpClient(OkHttp)
            val backForAppClient = BackForAppClient(httpClient)
            val viewModel: MainViewModel = viewModel {
                MainViewModel(dataStoreRepository, backForAppClient)
            }

            val userId by viewModel.userId
            val userProfile by viewModel.userProfile
            val isInitialized by viewModel.isInitialized

            if (isInitialized) {
                SetupNavGraph(
                    navController = rememberNavController(),
                    startDestination = if (userId.isEmpty() || userProfile == null) Screen.Auth else Screen.Home
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

