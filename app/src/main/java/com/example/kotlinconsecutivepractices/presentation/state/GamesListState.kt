package com.example.kotlinconsecutivepractices.presentation.state

import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity

interface GamesListState {
    val games: List<GameUiEntity>
    val nameFilter: String
    val yearFilter: String
    val isFiltersUsed: Boolean
    val error: String?
    val loading: Boolean
}