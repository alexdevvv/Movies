package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName

data class Trailer(
    @SerializedName("id")
    val id: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("site")
    val site: String

)