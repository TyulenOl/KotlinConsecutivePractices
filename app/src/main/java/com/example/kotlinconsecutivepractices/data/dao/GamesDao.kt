package com.example.kotlinconsecutivepractices.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinconsecutivepractices.data.model.GamesDbEntity

@Dao
interface GamesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gamesDbEntity: GamesDbEntity)

    @Delete
    suspend fun delete(gamesDbEntity: GamesDbEntity)

    @Query("SELECT * from GamesDbEntity WHERE id=:gameId")
    suspend fun getById(gameId: Int): GamesDbEntity?

    @Query("SELECT * from GamesDbEntity")
    suspend fun getAllGames(): List<GamesDbEntity>
}