package com.example.kotlinconsecutivepractices.domain.repository

import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity

interface IGamesRepository {
    public fun getGames(): List<GameEntity>
    public fun getGameDetailsById(gameId: Int): GameDetailEntity
}