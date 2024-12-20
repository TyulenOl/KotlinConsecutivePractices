package com.example.kotlinconsecutivepractices.presentation.model

data class GameUiEntity (
    val id: Int,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: String,
    val genres: List<String>
)