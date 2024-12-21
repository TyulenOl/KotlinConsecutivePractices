package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class AddedByStatus(
    @SerializedName("yet")
    val yet: Int?,

    @SerializedName("owned")
    val owned: Int?,

    @SerializedName("beaten")
    val beaten: Int?,

    @SerializedName("toplay")
    val toPlay: Int?,

    @SerializedName("dropped")
    val dropped: Int?,

    @SerializedName("playing")
    val playing: Int?
)
