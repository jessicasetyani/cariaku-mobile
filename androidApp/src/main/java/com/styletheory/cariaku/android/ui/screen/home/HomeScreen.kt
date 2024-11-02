package com.styletheory.cariaku.android.ui.screen.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.ui.screen.home.model.BottomMenuContent
import com.styletheory.cariaku.android.ui.screen.home.model.HistoryMenuItem
import com.styletheory.cariaku.android.ui.screen.home.viewModel.HomeViewModel
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import com.styletheory.cariaku.data.model.Assistant
import com.styletheory.cariaku.data.remote.BackForAppClient
import com.styletheory.cariaku.data.remote.createHttpClient
import io.ktor.client.engine.okhttp.OkHttp
import java.time.LocalTime

@Composable
fun HomeScreen(
    onChatRoomSelect: (String) -> Unit
) {
    val context = LocalContext.current
    val dataStoreRepository = remember {
        DataStoreRepository(dataStore = createDataStore(context = context))
    }
    val backForAppClient = remember {
        BackForAppClient(createHttpClient(OkHttp.create()))
    }
    val viewModel = remember {
        HomeViewModel(dataStoreRepository, backForAppClient)
    }

    val userName by viewModel.userName
    val topPopularAssistant by viewModel.topPopularAssistant

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val contentAreaHeight = screenHeight * 1f
    val contentAreaModifier = Modifier
        .height(contentAreaHeight)
        .verticalScroll(rememberScrollState())

    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize()
        ) {
            ContentArea(
                modifier = contentAreaModifier.padding(innerPadding),
                userName = userName,
                topPopularAssistant = topPopularAssistant,
                onOpenChat = onChatRoomSelect
            )
            BottomMenu(
                items = listOf(
                    BottomMenuContent("Home", Icons.Default.Home),
                    BottomMenuContent("Assistans", Icons.Default.SupportAgent),
                    BottomMenuContent("Profile", Icons.Default.Person),
                ),
                modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentArea(
    modifier: Modifier = Modifier,
    userName: String,
    topPopularAssistant: List<Assistant>,
    onOpenChat: (String) -> Unit
) {
    val chatHistories = listOf(
        HistoryMenuItem(
            "chatId1",
            "Chat 1: How to save money?",
            "This is summaries of How to save money?",
            LocalTime.now().minusMinutes(5)
        ),
        HistoryMenuItem(
            "chatId1",
            "Chat 2: Movie recommendations?",
            "This is summaries of Movie recommendations?",
            LocalTime.now().minusHours(1)
        ),
    )
    val trendingTopics = listOf(
        "Cara hemat uang jajan? CariAku tau nih!",
        "Mau tau rekomendasi film seru buat ditonton?",
        "Pengen dapet inspirasi buat dekorasi kamar?"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        val contentAreaHeight = LocalConfiguration.current.screenHeightDp.dp * 0.7f
        val andalanHeight = when(topPopularAssistant.size) {
            in 2..4 -> contentAreaHeight * 0.6f
            else -> contentAreaHeight * 0.3f
        }
        val historyHeight = contentAreaHeight * 0.5f

        GreetingSection(
            username = userName
        )
        Spacer(modifier = Modifier.height(8.dp))

        CariAkuAndalanSection(
            assistants = topPopularAssistant,
            onAssistantClick = { assistantId ->
                onOpenChat(assistantId)
            },
            modifier = Modifier.height(andalanHeight)
        )
        Spacer(modifier = Modifier.height(16.dp))

        CariAkuHistorySection(
            chatHistories = chatHistories,
            onOpenChat = { assistantId ->
                onOpenChat(assistantId)
            },
            modifier = Modifier.height(historyHeight)
        )

        Spacer(modifier = Modifier.height(16.dp))
        TrendingTopicSection(
            topics = trendingTopics
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}
