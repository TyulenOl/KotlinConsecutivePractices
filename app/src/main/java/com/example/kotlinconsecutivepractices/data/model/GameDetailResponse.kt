package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GameDetailResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("description_raw")
    val description: String?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("rating")
    val rating: Float?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("developers")
    val developers: List<Developer>?,

    @SerializedName("publishers")
    val publishers: List<Publisher>?,

    @SerializedName("platforms")
    val platforms: List<Platform>?,

    @SerializedName("genres")
    val genres: List<Genre>?
)
