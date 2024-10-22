package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R
import com.styletheory.cariaku.android.ui.screen.home.model.AssistantMenuContent
import com.styletheory.cariaku.android.ui.screen.home.model.BottomMenuContent
import com.styletheory.cariaku.android.ui.screen.home.model.HistoryMenuItem
import com.styletheory.cariaku.android.ui.theme.Beige1
import com.styletheory.cariaku.android.ui.theme.Beige2
import com.styletheory.cariaku.android.ui.theme.Beige3
import com.styletheory.cariaku.android.ui.theme.BlueViolet1
import com.styletheory.cariaku.android.ui.theme.BlueViolet2
import com.styletheory.cariaku.android.ui.theme.BlueViolet3
import com.styletheory.cariaku.android.ui.theme.LightGreen1
import com.styletheory.cariaku.android.ui.theme.LightGreen2
import com.styletheory.cariaku.android.ui.theme.LightGreen3
import com.styletheory.cariaku.android.ui.theme.OrangeYellow1
import com.styletheory.cariaku.android.ui.theme.OrangeYellow2
import com.styletheory.cariaku.android.ui.theme.OrangeYellow3
import com.styletheory.cariaku.data.local.DataStoreRepository
import com.styletheory.cariaku.data.local.createDataStore
import kotlinx.coroutines.flow.collectLatest
import java.time.LocalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onOpenChat: () -> Unit
) {
    val context = LocalContext.current
    val dataStoreRepository = remember {
        DataStoreRepository(dataStore = createDataStore(context = context))
    }
    var userName: String by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        dataStoreRepository.getUserId().collectLatest {
            userName = it
        }
    }

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
            ContentArea(modifier = Modifier.padding(innerPadding), userName, onOpenChat)
            BottomMenu(
                items = listOf(
                    BottomMenuContent("Home", Icons.Default.Home),
                    BottomMenuContent("Favorite", Icons.Default.Favorite),
                    BottomMenuContent("Profile", Icons.Default.Person),
                ), modifier = Modifier.align(Alignment.BottomCenter)
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ContentArea(modifier: Modifier = Modifier, userName: String, onOpenChat: () -> Unit) {
    val topAssistants = listOf(
        AssistantMenuContent("Assistant 1", R.drawable.ic_placeholder_assistant, BlueViolet1, BlueViolet2, BlueViolet3),
        AssistantMenuContent(
            "Assistant 2", R.drawable.ic_placeholder_assistant, LightGreen1, LightGreen2, LightGreen3
        ),
        AssistantMenuContent(
            "Assistant 3", R.drawable.ic_placeholder_assistant, OrangeYellow1, OrangeYellow2, OrangeYellow3
        ),
        AssistantMenuContent(
            "Assistant 4", R.drawable.ic_placeholder_assistant, Beige1, Beige2, Beige3
        )
    )
    val chatHistories = listOf(
        HistoryMenuItem("Chat 1: How to save money?", "This is summaries of How to save money?", LocalTime.now().minusMinutes(5)),
        HistoryMenuItem("Chat 2: Movie recommendations?", "This is summaries of Movie recommendations?", LocalTime.now().minusHours(1)),
    )
    val trendingTopics = listOf(
        "Cara hemat uang jajan? CariAku tau nih!",
        "Mau tau rekomendasi film seru buat ditonton?",
        "Pengen dapet inspirasi buat dekorasi kamar?"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        GreetingSection(username = userName)
        Spacer(modifier = Modifier.height(8.dp))

        CariAkuAndalanSection(assistantList = topAssistants)

        CariAkuHistorySection(chatHistories = chatHistories, onOpenChat = onOpenChat)

        TrendingTopicSection(topics = trendingTopics)
    }
}
