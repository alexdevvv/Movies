package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class ListTrailers(
    @SerializedName("id")
    val id: String,

    @SerializedName("trailers")
    val traillers: List<Trailer>,

)