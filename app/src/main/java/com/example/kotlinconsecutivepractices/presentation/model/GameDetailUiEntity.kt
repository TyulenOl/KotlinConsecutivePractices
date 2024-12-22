package com.example.kotlinconsecutivepractices.presentation.model

data class GameDetailUiEntity (
    val id: Int,
    val name: String,
    val description: String,
    val released: String,
    val backgroundImage: String?,
    val rating: String,
    val playtime: String,
    val developers: String,
    val publishers: String,
    val genres: List<String>,
    val platforms: List<String>
)
