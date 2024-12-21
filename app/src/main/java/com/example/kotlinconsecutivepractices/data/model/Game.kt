package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Game(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("tba")
    val tba: Boolean?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("rating")
    val rating: Float?,

    @SerializedName("rating_top")
    val ratingTop: Int?,

    @SerializedName("ratings")
    val ratings: List<Rating>?,

    @SerializedName("ratings_count")
    val ratingsCount: Int?,

    @SerializedName("reviews_text_count")
    val reviewsTextCount: String?,

    @SerializedName("genres")
    val genres: List<Genre>?,

    @SerializedName("added")
    val added: Int?,

    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,

    @SerializedName("metacritic")
    val metacritic: Int?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,

    @SerializedName("platforms")
    val platforms: List<Platform>?
)
