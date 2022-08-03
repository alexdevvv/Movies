package com.example.movies.domain

import com.example.movies.domain.models.Movie

class InserMovieInDbUseCase(private val dbRepository: DbRepository) {
    fun inserMovie(movie: Movie) =
        dbRepository.insertMovie(movie = movie)
}