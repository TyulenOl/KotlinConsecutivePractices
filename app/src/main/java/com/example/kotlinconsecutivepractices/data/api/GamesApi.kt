package com.example.kotlinconsecutivepractices.data.api

import com.example.kotlinconsecutivepractices.data.model.GameDetailResponse
import com.example.kotlinconsecutivepractices.data.model.GamesListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesApi {
    @GET("games")
    suspend fun getGames(
        @Query("page") page: Int = 1,
        @Query("page_size") pageSize: Int = 20,
        @Query("dates") dates: String = "",
        @Query("search") search: String = "",
        @Query("ordering") ordering: String = "-added",
        @Query("key") key: String
    ): GamesListResponse

    @GET("games/{id}")
    suspend fun getGameDetailById(
        @Path("id") id: Int,
        @Query("key") key: String
    ): GameDetailResponse
}