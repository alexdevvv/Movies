package com.example.movies.domain

class DeleteMovieFromBdUseCase(private val dbRepository: DbRepository) {
    fun deleteMovieById(id: Int) =
        dbRepository.removeMovie(id)
}