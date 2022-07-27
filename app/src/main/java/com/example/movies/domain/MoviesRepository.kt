package com.example.movies.domain

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.models.ReviewsResponse
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single

interface MoviesRepository {
    fun getTopMovie(page: Int): Single<MovieResponse>

    fun getTrailerForMovie(id: Int): Single<TrailerResponse>

    fun getReviews(filmId: Int): Single<ReviewsResponse>
}