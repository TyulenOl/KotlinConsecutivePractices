package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kotlinconsecutivepractices.presentation.screen.FavoritesScreen
import com.example.kotlinconsecutivepractices.presentation.screen.FiltersScreen
import com.example.kotlinconsecutivepractices.presentation.screen.GameDetailScreen
import com.example.kotlinconsecutivepractices.presentation.screen.GamesListScreen

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
        composable<FiltersRoute> {
            FiltersScreen()
        }
        composable<FavoritesRoute> {
            FavoritesScreen(onGameClick = { id ->
                navController.navigate(route = GameDetailRoute(id))
            },)
        }
        composable<GameDetailRoute> {
            GameDetailScreen()
        }
    }
}
