package com.example.movies.domain

import com.example.movies.domain.models.MovieResponse
import io.reactivex.Single

class GetTopMoviesFromServerUseCase(private val repository: NetworkRepository) {
    fun loadMovies(page: Int): Single<MovieResponse> {
        return repository.getTopMovie(page)
    }
}