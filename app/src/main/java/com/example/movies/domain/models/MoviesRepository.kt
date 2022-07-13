package com.example.movies.domain.models

import io.reactivex.Observable

interface MoviesRepository {
    fun getTopMovie(page: Int): Observable<MovieResponse>
}