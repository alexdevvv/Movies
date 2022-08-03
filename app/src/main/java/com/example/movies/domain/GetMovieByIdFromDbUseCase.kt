package com.example.movies.domain

class GetMovieByIdFromDbUseCase(private val dbRepository: DbRepository) {
    fun getMovieById(id: Int) =
        dbRepository.getMovieById(id = id)
}