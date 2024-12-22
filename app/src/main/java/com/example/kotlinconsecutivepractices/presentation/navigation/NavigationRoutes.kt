package com.example.kotlinconsecutivepractices.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object FavoritesRoute

@Serializable
data object GamesListRoute

@Serializable
data object FiltersRoute

@Serializable
data object ProfileRoute

@Serializable
data object EditProfileRoute

@Serializable
data class GameDetailRoute(val gameId: Int)