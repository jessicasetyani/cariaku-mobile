package com.styletheory.cariaku.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        installSplashScreen()
        setContent {
            AppContent()
        }
    }
}

@Composable
private fun AppContent() {
    MyApplicationTheme {
        Surface {
            val navController = rememberNavController()
            Navigation(navController = navController)
        }
    }
}


/**
 * A preview composable function that displays a preview of the WelcomePage.
 */
@Preview
@Composable
fun DefaultPreview() {
    val navController = rememberNavController()
    MyApplicationTheme {
        WelcomePage(navController = navController)
    }
}
