package com.example.kotlinconsecutivepractices.presentation.mappers

import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.presentation.model.GameDetailUiEntity
import com.example.kotlinconsecutivepractices.presentation.model.GameUiEntity

class GamesUiMapper {
    fun mapGameEntityToGameUiEntity(gameEntity: GameEntity): GameUiEntity {
        return GameUiEntity(
            gameEntity.id,
            gameEntity.name,
            gameEntity.released,
            gameEntity.backgroundImage,
            "%.2f".format(gameEntity.rating),
            gameEntity.genres,
            gameEntity.isFavorite
        )
    }

    fun mapGameUiEntityToGameEntity(gameUiEntity: GameUiEntity): GameEntity {
        return GameEntity(
            gameUiEntity.id,
            gameUiEntity.name,
            gameUiEntity.released,
            gameUiEntity.backgroundImage,
            gameUiEntity.rating.toFloat(),
            gameUiEntity.genres,
            gameUiEntity.isFavorite
        )
    }

    fun mapGameDetails(gameDetailEntity: GameDetailEntity): GameDetailUiEntity {
        return GameDetailUiEntity(
            gameDetailEntity.id,
            gameDetailEntity.name,
            gameDetailEntity.description,
            gameDetailEntity.released,
            gameDetailEntity.backgroundImage,
            "%.2f".format(gameDetailEntity.rating),
            "${gameDetailEntity.playtime} ч.",
            gameDetailEntity.developers.joinToString(),
            gameDetailEntity.publishers.joinToString(),
            gameDetailEntity.genres,
            gameDetailEntity.platforms
        )
    }
}