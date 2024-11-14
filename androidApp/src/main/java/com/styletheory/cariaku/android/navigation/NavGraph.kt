package com.styletheory.cariaku.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.styletheory.cariaku.android.ui.screen.auth.AuthScreen
import com.styletheory.cariaku.android.ui.screen.chat.ChatScreen
import com.styletheory.cariaku.android.ui.screen.home.HomeScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Auth.route) {
            AuthScreen(
                onAuthenticated = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Auth.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateBack = { navController.navigateUp() },
                onChatRoomSelect = { objectId ->
                    navController.navigate(Screen.Chat.withArgs(objectId))
                },
                navController = navController
            )
        }
        composable(Screen.Chat.route) { backStackEntry ->
            val objectId = backStackEntry.arguments?.getString("objectId") ?: ""
            ChatScreen(
                objectId = objectId,
                onNavigateBack = { navController.navigateUp() }
            )
        }
    }
}
