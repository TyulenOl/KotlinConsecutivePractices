package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Requirements(
    @SerializedName("minimum")
    val minimum: String?,

    @SerializedName("recommended")
    val recommended: String?
)
