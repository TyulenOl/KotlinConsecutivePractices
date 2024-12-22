package com.example.kotlinconsecutivepractices.data.repository

import com.example.kotlinconsecutivepractices.data.api.GamesApi
import com.example.kotlinconsecutivepractices.data.database.GamesDatabase
import com.example.kotlinconsecutivepractices.data.mappers.GamesDataMapper
import com.example.kotlinconsecutivepractices.data.model.GamesDbEntity
import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GamesRepository(
    private val api: GamesApi,
    private val mapper: GamesDataMapper,
    private val gamesDb: GamesDatabase
) : IGamesRepository {


    override suspend fun getGames(search: String?, year: String?): List<GameEntity> {
        val datesString = if (!year.isNullOrEmpty()) "${year}-01-01,${year}-12-31" else ""

        return withContext(Dispatchers.IO) {
            val games = mapper.mapGames(
                api.getGames(
                    key = "33fd9779090d42d8acb9ca8e62e469ee",
                    search = search.orEmpty(),
                    dates = datesString
                )
            )

            val favoriteGamesIds = getSavedGames().map { it.id }

            games.map {
                it.copy(isFavorite = favoriteGamesIds.contains(it.id))
            }
        }
    }

    override suspend fun getGameDetailsById(gameId: Int): GameDetailEntity {
        return mapper.mapGameDetail(
            api.getGameDetailById(
                id = gameId,
                key = "33fd9779090d42d8acb9ca8e62e469ee"
            )
        )
    }

    override suspend fun saveGame(gameEntity: GameEntity) {
        withContext(Dispatchers.IO) {
            gamesDb.gamesDao().insert(
                GamesDbEntity(
                    id = gameEntity.id,
                    name = gameEntity.name,
                    released = gameEntity.released,
                    backgroundImage = gameEntity.backgroundImage,
                    rating = gameEntity.rating,
                    genres = gameEntity.genres.joinToString(),
                )
            )
        }
    }

    override suspend fun removeGame(gameEntity: GameEntity) {
        withContext(Dispatchers.IO) {
            gamesDb.gamesDao().delete(
                GamesDbEntity(
                    id = gameEntity.id,
                    name = gameEntity.name,
                    released = gameEntity.released,
                    backgroundImage = gameEntity.backgroundImage,
                    rating = gameEntity.rating,
                    genres = gameEntity.genres.joinToString(),
                )
            )
        }
    }

    override suspend fun findSavedGameById(gameId: Int): GameEntity? {
        return withContext(Dispatchers.IO) {
            gamesDb.gamesDao().getById(gameId)?.let {
                GameEntity(
                    id = it.id ?: 0,
                    name = it.name.orEmpty(),
                    released = it.released.orEmpty(),
                    backgroundImage = it.backgroundImage.orEmpty(),
                    rating = it.rating ?: 0f,
                    genres = it.genres?.split(",") ?: emptyList(),
                    isFavorite = true
                )
            }
        }
    }

    override suspend fun getSavedGames(): List<GameEntity> {
        return withContext(Dispatchers.IO) {
            gamesDb.gamesDao().getAllGames().map {
                GameEntity(
                    id = it.id ?: 0,
                    name = it.name.orEmpty(),
                    released = it.released.orEmpty(),
                    backgroundImage = it.backgroundImage.orEmpty(),
                    rating = it.rating ?: 0f,
                    genres = it.genres?.split(",") ?: emptyList(),
                    true
                )
            }
        }
    }
}