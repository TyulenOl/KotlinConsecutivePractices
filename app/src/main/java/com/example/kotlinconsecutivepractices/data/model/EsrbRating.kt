package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class EsrbRating(
    @SerializedName("id")
    val id: Int?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("name")
    val name: String?
)
