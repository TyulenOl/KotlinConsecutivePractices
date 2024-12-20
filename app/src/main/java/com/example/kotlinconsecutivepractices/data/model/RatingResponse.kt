package com.example.kotlinconsecutivepractices.data.model

data class RatingResponse (
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Double
)