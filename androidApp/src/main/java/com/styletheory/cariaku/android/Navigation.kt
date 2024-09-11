package com.styletheory.cariaku.android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcomePage") {
        composable("welcomePage") {
            WelcomePage(navController = navController)
        }
        composable("chatScreen") {
            ChatScreen()
        }
    }
}
