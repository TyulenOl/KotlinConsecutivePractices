package com.example.kotlinconsecutivepractices.data.model

data class GamePaginationResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<GameResponse>
)