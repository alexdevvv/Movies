package com.example.movies.domain

class GetAllMoviesFromDbUseCase(private val dbRepository: DbRepository) {
    fun getAllMoviesFromDb() =
        dbRepository.getAllFavoriteMovies()
}