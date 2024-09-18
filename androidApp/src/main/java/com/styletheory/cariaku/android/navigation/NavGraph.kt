package com.styletheory.cariaku.android.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.styletheory.cariaku.android.ui.screen.WelcomePage
import com.styletheory.cariaku.android.ui.screen.chat.ChatScreen
import com.styletheory.cariaku.android.ui.screen.chat.ChatViewModel
import com.styletheory.cariaku.android.ui.screen.chat.ChatViewModelFactory
import com.styletheory.cariaku.data.remote.OpenRouterClient
import com.styletheory.cariaku.data.remote.createHttpClient
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.util.Constant.API_KEY_OPEN_ROUTE
import io.ktor.client.engine.okhttp.OkHttp

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: Screen
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable<Screen.Home> {
            WelcomePage(
                onOpenChat = {
                    navController.navigate(Screen.Chat)
                },
                navController = navController
            )
        }
        composable<Screen.Chat> {
            ChatScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}