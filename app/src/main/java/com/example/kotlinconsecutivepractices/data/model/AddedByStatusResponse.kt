package com.example.kotlinconsecutivepractices.data.model

data class AddedByStatusResponse(
    val yet: Int?,
    val owned: Int?,
    val beaten: Int?,
    val toPlay: Int?,
    val dropped: Int?,
    val playing: Int?
)