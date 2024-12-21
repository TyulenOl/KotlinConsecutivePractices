package com.example.kotlinconsecutivepractices.presentation.state

import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity

interface GamesListState {
    val games: List<GameUiEntity>
    val error: String?
    val loading: Boolean
}