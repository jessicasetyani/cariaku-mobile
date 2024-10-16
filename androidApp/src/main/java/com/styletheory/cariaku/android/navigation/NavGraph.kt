package com.styletheory.cariaku.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.styletheory.cariaku.android.ui.screen.WelcomePage
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
                    navController.navigate(Screen.Home)
                }
            )
        }
        composable<Screen.Home> {
            HomeScreen(
                onOpenChat = {
                    navController.navigate(Screen.Chat)
                }
            )
        }
//        composable<Screen.Home> {
//            WelcomePage(
//                onOpenChat = {
//                    navController.navigate(Screen.Chat)
//                },
//                navController = navController
//            )
//        }
        composable<Screen.Chat> {
            ChatScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}