package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItems<T>(val name: String, val icon: ImageVector, val route: T) {
    data object GamesList : BottomBarItems<GamesListRoute>(
        name = "Games",
        icon = Icons.AutoMirrored.Rounded.List,
        route = GamesListRoute
    )

    data object Favorites : BottomBarItems<FavoritesRoute>(
        name = "Favorites",
        icon = Icons.Default.Favorite,
        route = FavoritesRoute
    )

    data object Settings : BottomBarItems<SettingsRoute>(
        name = "Settings",
        icon = Icons.Default.Settings,
        route = SettingsRoute
    )
}
