package com.example.kotlinconsecutivepractices.presentation.state

import com.example.kotlinconsecutivepractices.presentation.model.GameDetailUiEntity

interface GameDetailState {
    val gameDetail: GameDetailUiEntity?
    val error: String?
    val loading: Boolean
}