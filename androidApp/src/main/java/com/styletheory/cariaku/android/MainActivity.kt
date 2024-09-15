package com.styletheory.cariaku.android

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController

/**
 * The main activity of the application.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            installSplashScreen()
            MyApplicationTheme {
                Surface {
                    val navController = rememberNavController()
                    Navigation(navController = navController)
                }
            }
        }
    }
}

/**
 * A composable function that defines the application's theme.
 *
 * @param content The content to be displayed within the theme.
 */
@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    val colors = MaterialTheme.colorScheme
    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = colors.background.toArgb()
        window.navigationBarColor = colors.background.toArgb()
        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = true
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
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
