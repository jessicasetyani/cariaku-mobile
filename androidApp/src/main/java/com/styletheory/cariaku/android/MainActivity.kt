package com.styletheory.cariaku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.styletheory.cariaku.android.navigation.Screen
import com.styletheory.cariaku.android.navigation.SetupNavGraph
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
            SetupNavGraph(
                navController = rememberNavController(),
                startDestination = Screen.Home
                // check current user
               // if() Screen.Home
               // else Screen.Auth
            )
        }
    }
}
