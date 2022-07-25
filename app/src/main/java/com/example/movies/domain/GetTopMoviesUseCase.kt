package com.example.movies.domain

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.models.MoviesRepository
import io.reactivex.Single

class GetTopMoviesUseCase(private val repository: MoviesRepository) {
    fun loadMovies(page: Int): Single<MovieResponse> {
        return repository.getTopMovie(page)
    }

}