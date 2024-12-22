package com.example.kotlinconsecutivepractices.presentation.state

import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity

interface FavoriteState {
    val games: List<GameUiEntity>
}