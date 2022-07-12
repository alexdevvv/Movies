package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class Poster(
    @SerializedName("url")
    val url: String
)