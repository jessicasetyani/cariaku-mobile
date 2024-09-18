package com.styletheory.cariaku.android

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.styletheory.cariaku.android.chat.ChatScreen
import com.styletheory.cariaku.android.chat.ChatViewModel
import com.styletheory.cariaku.data.OpenRouterClient
import com.styletheory.cariaku.data.repository.ChatRepository
import com.styletheory.cariaku.network.createHttpClient
import com.styletheory.cariaku.util.Constant.API_KEY_OPEN_ROUTE
import io.ktor.client.engine.okhttp.OkHttp

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcomePage") {
        composable("welcomePage") {
            WelcomePage(navController = navController)
        }
        composable("chatScreen") {
            val client = remember {
                OpenRouterClient(createHttpClient(OkHttp.create(), API_KEY_OPEN_ROUTE))
            }
            val chatRepository = remember { ChatRepository(client) }
            val chatViewModel: ChatViewModel = viewModel(factory = ChatViewModelFactory(chatRepository))
            ChatScreen(
                viewModel = chatViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
