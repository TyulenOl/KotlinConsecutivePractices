package com.example.kotlinconsecutivepractices.data.model

data class GameResponse(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String?,
    val tba: Boolean,
    val backgroundImage: String?,
    val rating: Double,
    val ratingTop: Int,
    val ratings: List<RatingResponse>,
    val ratingsCount: Int,
    val reviewsTextCount: String,
    val added: Int,
    val addedByStatus: AddedByStatusResponse,
    val metacritic: Int,
    val playtime: Int,
    val suggestionsCount: Int,
    val updated: String,
    val esrbRating: EsrbRatingResponse?,
    val platforms: List<PlatformResponse>
)