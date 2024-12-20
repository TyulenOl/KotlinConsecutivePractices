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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kotlinconsecutivepractices.presentation.navigation.AppBottomNavigationBar
import com.example.kotlinconsecutivepractices.presentation.navigation.NavigationGraph
import com.example.kotlinconsecutivepractices.ui.theme.KotlinConsecutivePracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
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
    Scaffold(
        bottomBar = {
            AppBottomNavigationBar(
                navController = navController
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