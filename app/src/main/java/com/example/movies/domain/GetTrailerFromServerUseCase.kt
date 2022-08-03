package com.example.movies.domain

import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single

class GetTrailerFromServerUseCase(private val repository: NetworkRepository) {
    fun getTrailerForMovie(id: Int):Single<TrailerResponse> =
        repository.getTrailerForMovie(id)
}