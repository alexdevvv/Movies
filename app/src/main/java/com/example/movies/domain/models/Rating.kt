package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("kp")
    val kp: Double
)