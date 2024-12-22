package com.example.kotlinconsecutivepractices.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class GamesDbEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,

    @ColumnInfo("name")
    val name: String?,

    @ColumnInfo("released")
    val released: String?,

    @ColumnInfo("backgroundImage")
    val backgroundImage: String?,

    @ColumnInfo("rating")
    val rating: Float?,

    @ColumnInfo("genres")
    val genres: String?
)