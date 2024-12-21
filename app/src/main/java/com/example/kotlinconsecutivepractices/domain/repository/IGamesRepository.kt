package com.example.kotlinconsecutivepractices.domain.repository

import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity

interface IGamesRepository {
    suspend fun getGames(): List<GameEntity>
    suspend fun getGameDetailsById(gameId: Int): GameDetailEntity
}