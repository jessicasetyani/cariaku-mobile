package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
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

@Composable
fun ContentArea(modifier: Modifier = Modifier, username: String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        GreetingSection(username = username)
        Spacer(modifier = Modifier.height(8.dp))
        CariAkuAndalanSection(
            chips = listOf(
                AssistantMenuContent("Assistant 1", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 2", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 3", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 4", R.drawable.ic_placeholder_assistant)
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        CariAkuHistorySection()
    }
}
