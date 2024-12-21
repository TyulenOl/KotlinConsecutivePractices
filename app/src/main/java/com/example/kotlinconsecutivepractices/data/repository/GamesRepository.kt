package com.example.kotlinconsecutivepractices.data.repository

import com.example.kotlinconsecutivepractices.data.api.GamesApi
import com.example.kotlinconsecutivepractices.data.mappers.GamesDataMapper
import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(
    private val api: GamesApi,
    private val mapper: GamesDataMapper
) : IGamesRepository {


    override suspend fun getGames(): List<GameEntity> {
        return withContext(Dispatchers.IO) {
            mapper.mapGames(api.getGames(key = "33fd9779090d42d8acb9ca8e62e469ee"))
        }
    }

    override suspend fun getGameDetailsById(gameId: Int): GameDetailEntity {
        return mapper.mapGameDetail(api.getGameDetailById(id = gameId, key = "33fd9779090d42d8acb9ca8e62e469ee"))
    }
}