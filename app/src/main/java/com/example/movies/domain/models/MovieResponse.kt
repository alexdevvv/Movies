package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

class MovieResponse(
    @SerializedName("docs")
    val movies: List<Movie>
)