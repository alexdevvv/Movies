package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @SerializedName("kp")
    val kp: Double
): Serializable