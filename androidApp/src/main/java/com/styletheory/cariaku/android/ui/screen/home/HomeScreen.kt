package com.styletheory.cariaku.android.ui.screen.home

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import com.styletheory.cariaku.android.R
import com.styletheory.cariaku.android.domain.UserProfileTable
import com.styletheory.cariaku.android.domain.UserTable
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
    var userName = remember { "" }

    // Create a query for checking user name
    val userQuery = ParseQuery.getQuery<ParseObject>(UserTable.TABLE_NAME)
    userQuery.whereEqualTo(
        UserTable.OBJECT_ID,
        ParseUser.getCurrentUser().objectId
    )

    userQuery.findInBackground { users, messageError ->
        if(messageError == null) {
            if(users.isNullOrEmpty()) println("user profile name not fouund")
            else {
                val parseUser = users.map { user ->
                    val userProfileObjectId = user.objectId
                }
                users.first().getParseUser(UserTable.USER_PROFILE)
            }
        } else {

        }
    }


    val getUsersQuery = ParseQuery.getQuery<ParseObject>(UserTable.TABLE_NAME)
    getUsersQuery.whereEqualTo(UserTable.OBJECT_ID, ParseUser.getCurrentUser().objectId)
    try {
        getUsersQuery.findInBackground { objects: List<ParseObject>?, e: ParseException? ->
            if(e == null) {
               val parseData = objects?.map { user->
                   val userProfile = user.getMap<String>(
                       UserProfileTable.OBJECT_ID
                   ) ?: emptyMap()
                       userName = userProfile.values.first().toString()
               }
            } else {
                Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        }
    } catch(e: ParseException) {

        e.printStackTrace()
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
            ContentArea(modifier = Modifier.padding(innerPadding), userName)
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
fun ContentArea(modifier: Modifier = Modifier, userName: String) {
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

        CariAkuHistorySection(chatHistories = chatHistories)

        TrendingTopicSection(topics = trendingTopics)
    }
}
