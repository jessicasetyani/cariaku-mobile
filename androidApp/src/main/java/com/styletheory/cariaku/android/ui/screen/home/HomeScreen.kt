package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import java.time.LocalTime

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onOpenChat: () -> Unit
) {
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
            ContentArea(modifier = Modifier.padding(innerPadding), username = "Jessica")
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
fun ContentArea(modifier: Modifier = Modifier, username: String) {
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
        GreetingSection(username = username)
        Spacer(modifier = Modifier.height(8.dp))

        CariAkuAndalanSection(assistantList = topAssistants)
        Spacer(modifier = Modifier.height(8.dp))

        CariAkuHistorySection(chatHistories = chatHistories)
        Spacer(modifier = Modifier.height(8.dp))

        TrendingTopicSection(topics = trendingTopics)
    }
}
