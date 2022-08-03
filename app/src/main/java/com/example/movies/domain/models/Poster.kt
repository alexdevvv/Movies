package com.example.movies.domain.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Poster(
    @SerializedName("url")
    val url: String
): Serializable