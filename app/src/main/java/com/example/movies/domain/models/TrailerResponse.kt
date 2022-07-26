package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("videos")
    val listTrailers: ListTrailers

)
