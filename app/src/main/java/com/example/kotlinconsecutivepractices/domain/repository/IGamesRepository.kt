package com.example.kotlinconsecutivepractices.domain.repository

import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity

interface IGamesRepository {
    suspend fun getGames(search: String? = null, year: String? = null): List<GameEntity>
    suspend fun getGameDetailsById(gameId: Int): GameDetailEntity
    suspend fun saveGame(gameEntity: GameEntity)
    suspend fun removeGame(gameEntity: GameEntity)
    suspend fun findSavedGameById(gameId: Int): GameEntity?
    suspend fun getSavedGames(): List<GameEntity>
}