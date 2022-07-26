package com.example.movies.domain

import com.example.movies.domain.models.MoviesRepository
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single

class GetTrailerForMovieUseCase(val repository: MoviesRepository) {
    fun getTrailerForMovie(id: Int):Single<TrailerResponse> =
        repository.getTrailerForMovie(id)
}