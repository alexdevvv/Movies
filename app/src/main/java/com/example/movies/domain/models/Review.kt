package com.example.movies.domain.models

data class Review(
    val __v: Int,
    val _id: String,
    val author: String,
    val authorId: Int,
    val date: String,
    val id: Int,
    val movieId: Int,
    val review: String,
    val title: String,
    val type: String,
    val updatedAt: String,
    val userRating: Double
)