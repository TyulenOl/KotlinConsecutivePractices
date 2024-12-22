package com.example.kotlinconsecutivepractices.di

import android.content.Context
import androidx.room.Room
import com.example.kotlinconsecutivepractices.data.database.GamesDatabase
import org.koin.dsl.module
import java.util.Objects.isNull

val dbModule = module {
    single<GamesDatabase> { DataBaseBuilder.getInstance(get()) }
}

object DataBaseBuilder {

    private var INSTANCE: GamesDatabase? = null

    fun getInstance(context: Context): GamesDatabase {
        if (isNull(INSTANCE)) {
            synchronized(GamesDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }

        return INSTANCE!!
    }

    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            GamesDatabase::class.java,
            "games-favorite"
        ).build()
}