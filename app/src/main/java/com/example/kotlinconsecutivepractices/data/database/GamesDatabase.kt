package com.example.kotlinconsecutivepractices.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kotlinconsecutivepractices.data.dao.GamesDao
import com.example.kotlinconsecutivepractices.data.model.GamesDbEntity

@Database(entities = [GamesDbEntity::class], version = 1)
abstract class GamesDatabase: RoomDatabase() {
    abstract fun gamesDao(): GamesDao
}