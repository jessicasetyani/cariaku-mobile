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
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Auth> {
            AuthScreen(
                onAuthenticated = {
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable<Screen.Home> { entry ->
            HomeScreen(
                onNavigateBack = { navController.navigateUp() },
                onChatRoomSelect = { objectId ->
                    navController.navigate("${Screen.Chat.route}".replace("{objectId}", objectId))
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
