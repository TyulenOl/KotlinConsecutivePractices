package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Reactions(
    @SerializedName("positive")
    val positive: Int?,

    @SerializedName("neutral")
    val neutral: Int?,

    @SerializedName("negative")
    val negative: Int?
)
