package com.example.kotlinconsecutivepractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinconsecutivepractices.presentation.navigation.AppBottomNavigationBar
import com.example.kotlinconsecutivepractices.presentation.navigation.NavigationGraph
import com.example.kotlinconsecutivepractices.presentation.utils.FiltersPinCache
import com.example.kotlinconsecutivepractices.presentation.utils.NotificationChannelManager
import com.example.kotlinconsecutivepractices.ui.theme.KotlinConsecutivePracticesTheme
import org.koin.compose.koinInject

class MainActivity : ComponentActivity() {
    private val channelManager: NotificationChannelManager by lazy {
        NotificationChannelManager(NotificationManagerCompat.from(this), this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        channelManager.createNotificationChannels()
        setContent {
            KotlinConsecutivePracticesTheme(dynamicColor = false) {
                val navController = rememberNavController()

                DefaultAppScreen(navController)
            }
        }
    }
}

@Composable
fun DefaultAppScreen(navController: NavHostController) {
    val filtersPinCache = koinInject<FiltersPinCache>()

    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(
                navController = navController,
                filtersPinCache = filtersPinCache
            )
        }
    )
    { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues))
        {
            NavigationGraph(navController)
        }
    }
}

@Composable
@Preview
fun DefaultAppScreenPreview() {
    DefaultAppScreen(navController = rememberNavController())
}