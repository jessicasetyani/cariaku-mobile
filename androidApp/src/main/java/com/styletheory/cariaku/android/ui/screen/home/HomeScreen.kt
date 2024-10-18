package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.styletheory.cariaku.android.R
import com.styletheory.cariaku.android.ui.screen.home.model.AssistantMenuContent

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
        ContentArea(modifier = Modifier.padding(innerPadding), username = "Jessica")
    }

}

@Composable
fun ContentArea(modifier: Modifier = Modifier, username: String) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingSection(username = username)
        CariAkuAndalanSection(
            chips = listOf(
                AssistantMenuContent("Assistant 1", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 2", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 3", R.drawable.ic_placeholder_assistant),
                AssistantMenuContent("Assistant 4", R.drawable.ic_placeholder_assistant)
            )
        )
        
    }
}
