package com.styletheory.cariaku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.styletheory.cariaku.android.navigation.Screen
import com.styletheory.cariaku.android.navigation.SetupNavGraph
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import kotlinx.coroutines.flow.collectLatest
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
            val scope = rememberCoroutineScope()
            val dataStoreRepository = remember {
                DataStoreRepository(dataStore = createDataStore(context = context))
            }
            var userId: String? by remember { mutableStateOf(null) }
            var isInitialized by remember { mutableStateOf(false) }

            LaunchedEffect(Unit) {
                dataStoreRepository.getUserId().collectLatest {
                    userId = it
                    isInitialized = true
                }
            }

            if (isInitialized) {
                SetupNavGraph(
                    navController = rememberNavController(),
                    startDestination = if (userId.isNullOrEmpty()) Screen.Auth else Screen.Home
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
