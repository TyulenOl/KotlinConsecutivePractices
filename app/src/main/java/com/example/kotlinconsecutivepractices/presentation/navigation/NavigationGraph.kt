package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinconsecutivepractices.presentation.screen.FavoritesScreen
import com.example.kotlinconsecutivepractices.presentation.screen.GameDetailScreen
import com.example.kotlinconsecutivepractices.presentation.screen.GamesListScreen
import com.example.kotlinconsecutivepractices.presentation.screen.SettingsScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarItems.GamesList.route
    ) {
        composable<GamesListRoute> {
            GamesListScreen(
                onGameClick = { id ->
                    navController.navigate(route = GameDetailRoute(id))
                }
            )
        }
        composable<FavoritesRoute> {
            FavoritesScreen()
        }
        composable<SettingsRoute> {
            SettingsScreen()
        }
        composable<GameDetailRoute> {
            GameDetailScreen()
        }
    }
}
