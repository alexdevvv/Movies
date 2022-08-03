package com.example.movies.domain.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("poster")
    @Embedded
    val poster: Poster,

    @SerializedName("description")
    val description: String,

    @SerializedName("rating")
    @Embedded
    val rating: Rating,

    @SerializedName("year")
    val year: Int
): Serializable