package com.example.kotlinconsecutivepractices.data.mappers

import com.example.kotlinconsecutivepractices.data.model.GameDetailResponse
import com.example.kotlinconsecutivepractices.data.model.GamesListResponse
import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity

class GamesDataMapper {
    fun mapGames(gamesListResponse: GamesListResponse): List<GameEntity> {
        return gamesListResponse.results?.map {
            GameEntity(
                it.id ?: 0,
                it.name.orEmpty(),
                it.released.orEmpty(),
                it.backgroundImage.orEmpty(),
                it.rating ?: 0F,
                it.genres?.map { genre -> genre.name.orEmpty() } ?: emptyList()
            )
        }.orEmpty()
    }

    fun mapGameDetail(gameDetailResponse: GameDetailResponse): GameDetailEntity {
        return GameDetailEntity(
                id = gameDetailResponse.id ?: 0,
                name = gameDetailResponse.name.orEmpty(),
                description = gameDetailResponse.description.orEmpty(),
                released = gameDetailResponse.released.orEmpty(),
                backgroundImage = gameDetailResponse.backgroundImage.orEmpty(),
                rating = gameDetailResponse.rating ?: 0f,
                playtime = gameDetailResponse.playtime ?: 0,
                developers = gameDetailResponse.developers?.map { it.name.orEmpty() } ?: emptyList(),
                publishers = gameDetailResponse.publishers?.map { it.name.orEmpty() } ?: emptyList(),
                genres = gameDetailResponse.genres?.map { it.name.orEmpty() } ?: emptyList(),
                platforms = gameDetailResponse.platforms?.map { it.platformDetail?.name.orEmpty() } ?: emptyList()
            )

    }
}