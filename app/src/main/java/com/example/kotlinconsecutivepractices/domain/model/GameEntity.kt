package com.example.kotlinconsecutivepractices.domain.model

import java.time.LocalDate

data class GameEntity(
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: Float,
    val genres: List<String>
)