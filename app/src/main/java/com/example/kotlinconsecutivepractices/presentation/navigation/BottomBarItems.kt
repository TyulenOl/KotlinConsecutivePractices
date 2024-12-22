package com.example.kotlinconsecutivepractices.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarItems<T>(val name: String, val icon: ImageVector, val route: T) {
    data object GamesList : BottomBarItems<GamesListRoute>(
        name = "Игры",
        icon = Icons.AutoMirrored.Rounded.List,
        route = GamesListRoute
    )

    data object Filters : BottomBarItems<FiltersRoute>(
        name = "Фильтры",
        icon = Icons.Default.Search,
        route = FiltersRoute
    )

    data object Favorites : BottomBarItems<FavoritesRoute>(
        name = "Избранное",
        icon = Icons.Default.Favorite,
        route = FavoritesRoute
    )

    data object Profile : BottomBarItems<ProfileRoute>(
        name = "Профиль",
        icon = Icons.Default.Person,
        route = ProfileRoute
    )
}
