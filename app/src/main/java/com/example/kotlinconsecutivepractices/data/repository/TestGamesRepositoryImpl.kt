package com.example.kotlinconsecutivepractices.data.repository

import com.example.kotlinconsecutivepractices.domain.model.GameDetailEntity
import com.example.kotlinconsecutivepractices.domain.model.GameEntity
import com.example.kotlinconsecutivepractices.domain.repository.IGamesRepository

class TestGamesRepositoryImpl : IGamesRepository {
    private val games: List<GameEntity> = listOf(
        GameEntity(
            3498,
            "Grand Theft Auto V",
            "2013-09-17",
            "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
            4.47F,
            listOf("Singleplayer", "Steam Achievements", "Multiplayer", "Full controller support", "Atmospheric", "Great Soundtrack", "RPG", "Co-op", "Open World", "cooperative", "First-Person", "Third Person", "Funny", "Sandbox", "Comedy", "Crime"),
        ),
        GameEntity(
            3328,
            "The Witcher 3: Wild Hunt",
            "2015-05-18",
            "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
            4.65F,
            listOf("Action", "RPG")
        ),
        GameEntity(
            4200,
            "Portal 2",
            "2011-04-18",
            "https://media.rawg.io/media/games/2ba/2bac0e87cf45e5b508f227d281c9252a.jpg",
            4.6F,
            listOf("Shooter", "Puzzle")
        ),
    )

    private val gamesDetails: List<GameDetailEntity> = listOf(
        GameDetailEntity(
            3498,
            "Grand Theft Auto V",
            "Rockstar Games went bigger, since their previous installment of the series. You get the complicated and realistic world-building from Liberty City of GTA4 in the setting of lively and diverse Los Santos, from an old fan favorite GTA San Andreas. 561 different vehicles (including every transport you can operate) and the amount is rising with every update. \nSimultaneous storytelling from three unique perspectives: \nFollow Michael, ex-criminal living his life of leisure away from the past, Franklin, a kid that seeks the better future, and Trevor, the exact past Michael is trying to run away from. \nGTA Online will provide a lot of additional challenge even for the experienced players, coming fresh from the story mode. Now you will have other players around that can help you just as likely as ruin your mission. Every GTA mechanic up to date can be experienced by players through the unique customizable character, and community content paired with the leveling system tends to keep everyone busy and engaged.\n\nEspañol\nRockstar Games se hizo más grande desde su entrega anterior de la serie. Obtienes la construcción del mundo complicada y realista de Liberty City de GTA4 en el escenario de Los Santos, un viejo favorito de los fans, GTA San Andreas. 561 vehículos diferentes (incluidos todos los transportes que puede operar) y la cantidad aumenta con cada actualización.\nNarración simultánea desde tres perspectivas únicas:\nSigue a Michael, ex-criminal que vive su vida de ocio lejos del pasado, Franklin, un niño que busca un futuro mejor, y Trevor, el pasado exacto del que Michael está tratando de huir.\nGTA Online proporcionará muchos desafíos adicionales incluso para los jugadores experimentados, recién llegados del modo historia. Ahora tendrás otros jugadores cerca que pueden ayudarte con la misma probabilidad que arruinar tu misión. Los jugadores pueden experimentar todas las mecánicas de GTA actualizadas a través del personaje personalizable único, y el contenido de la comunidad combinado con el sistema de nivelación tiende a mantener a todos ocupados y comprometidos.",
            "2013-09-17",
            "https://media.rawg.io/media/games/20a/20aa03a10cda45239fe22d035c0ebe64.jpg",
            4.47F,
            74,
            listOf("Rockstar"),
            listOf("Rockstar"),
            listOf("Singleplayer", "Steam Achievements", "Multiplayer", "Full controller support", "Atmospheric", "Great Soundtrack", "RPG", "Co-op", "Open World", "cooperative", "First-Person", "Third Person", "Funny", "Sandbox", "Comedy", "Crime"),
            listOf("PC", "Xbox")
        ),
        GameDetailEntity(
            3328,
            "The Witcher 3: Wild Hunt",
            "The Witcher 3: Wild Hunt description",
            "2015-05-18",
            "https://media.rawg.io/media/games/618/618c2031a07bbff6b4f611f10b6bcdbc.jpg",
            4.65F,
            146,
            listOf("CD PROJEKT RED"),
            listOf("CD PROJEKT RED"),
            listOf("Action", "RPG"),
            listOf("PC", "Xbox")
        ),
        GameDetailEntity(
            4200,
            "Portal 2",
            "Portal 2 description",
            "2011-04-18",
            "https://media.rawg.io/media/games/2ba/2bac0e87cf45e5b508f227d281c9252a.jpg",
            4.6F,
            32,
            listOf("Valve"),
            listOf("Valve"),
            listOf("Shooter", "Puzzle"),
            listOf("PC")
        )
    )

    override fun getGames(): List<GameEntity> {
        return games
    }

    override fun getGameDetailsById(gameId: Int): GameDetailEntity {
        return gamesDetails.first { it.id == gameId }
    }
}