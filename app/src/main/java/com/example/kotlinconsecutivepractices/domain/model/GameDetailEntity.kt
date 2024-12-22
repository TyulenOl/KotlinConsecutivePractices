package com.example.kotlinconsecutivepractices.domain.model

data class GameDetailEntity (
    val id: Int,
    val name: String,
    val description: String,
    val released: String,
    val backgroundImage: String?,
    val rating: Float,
    val playtime: Int,
    val developers: List<String>,
    val publishers: List<String>,
    val genres: List<String>,
    val platforms: List<String>
)
