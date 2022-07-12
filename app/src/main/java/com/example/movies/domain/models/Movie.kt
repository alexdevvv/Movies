package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("poster")
    val poster: Poster,

    @SerializedName("description")
    val description: String,

    @SerializedName("rating")
    val rating: Rating,

    @SerializedName("year")
    val year: Int
)