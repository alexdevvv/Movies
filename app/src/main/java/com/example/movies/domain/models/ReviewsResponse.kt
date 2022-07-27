package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class ReviewsResponse(
    @SerializedName("docs")
    val docs: List<Review>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)