package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class GameDetailResponse(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("name_original")
    val nameOriginal: String?,

    @SerializedName("description_raw")
    val description: String?,

    @SerializedName("metacritic")
    val metacritic: Int?,

    @SerializedName("metacritic_platforms")
    val metacriticPlatforms: List<GamePlatformMetacritic>?,

    @SerializedName("metascore")
    val metascore: Int?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("released")
    val released: String?,

    @SerializedName("tba")
    val tba: Boolean?,

    @SerializedName("updated")
    val updated: String?,

    @SerializedName("background_image")
    val backgroundImage: String?,

    @SerializedName("background_image_additional")
    val backgroundImageAdditional: String?,

    @SerializedName("website")
    val website: String?,

    @SerializedName("rating")
    val rating: Float?,

    @SerializedName("rating_top")
    val ratingTop: Int?,

    @SerializedName("ratings")
    val ratings: List<Rating>?,

    @SerializedName("reactions")
    val reactions: Reactions?,

    @SerializedName("added")
    val added: Int?,

    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatus?,

    @SerializedName("playtime")
    val playtime: Int?,

    @SerializedName("screenshots_count")
    val screenshotsCount: Int?,

    @SerializedName("movies_count")
    val moviesCount: Int?,

    @SerializedName("creators_count")
    val creatorsCount: Int?,

    @SerializedName("achievements_count")
    val achievementsCount: Int?,

    @SerializedName("parent_achievements_count")
    val parentAchievementsCount: String?,

    @SerializedName("reddit_url")
    val redditUrl: String?,

    @SerializedName("reddit_name")
    val redditName: String?,

    @SerializedName("reddit_description")
    val redditDescription: String?,

    @SerializedName("reddit_logo")
    val redditLogo: String?,

    @SerializedName("reddit_count")
    val redditCount: Int?,

    @SerializedName("twitch_count")
    val twitchCount: String?,

    @SerializedName("youtube_count")
    val youtubeCount: String?,

    @SerializedName("reviews_text_count")
    val reviewsTextCount: String?,

    @SerializedName("ratings_count")
    val ratingsCount: Int?,

    @SerializedName("suggestions_count")
    val suggestionsCount: Int?,

    @SerializedName("alternative_names")
    val alternativeNames: List<String>?,

    @SerializedName("metacritic_url")
    val metacriticUrl: String?,

    @SerializedName("parents_count")
    val parentsCount: Int?,

    @SerializedName("additions_count")
    val additionsCount: Int?,

    @SerializedName("game_series_count")
    val gameSeriesCount: Int?,

    @SerializedName("esrb_rating")
    val esrbRating: EsrbRating?,

    @SerializedName("developers")
    val developers: List<Developer>?,

    @SerializedName("publishers")
    val publishers: List<Publisher>?,

    @SerializedName("platforms")
    val platforms: List<Platform>?,

    @SerializedName("genres")
    val genres: List<Genre>?
)
