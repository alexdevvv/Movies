package com.example.movies.domain.models

import io.reactivex.Single

interface MoviesRepository {
    fun getTopMovie(page: Int): Single<MovieResponse>
}