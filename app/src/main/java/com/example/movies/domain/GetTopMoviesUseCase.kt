package com.example.movies.domain

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.models.MoviesRepository
import io.reactivex.Observable
import io.reactivex.Single

class GetTopMoviesUseCase(private val repository: MoviesRepository) {
    fun getTopMovie(page: Int): Single<MovieResponse> =
        repository.getTopMovie(page)

}