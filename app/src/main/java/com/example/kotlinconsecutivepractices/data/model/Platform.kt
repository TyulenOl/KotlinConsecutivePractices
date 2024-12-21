package com.example.kotlinconsecutivepractices.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Platform(
    @SerializedName("platform")
    val platformDetail: PlatformDetail?,

    @SerializedName("released_at")
    val releasedAt: String?,

    @SerializedName("requirements")
    val requirements: Requirements?
)
