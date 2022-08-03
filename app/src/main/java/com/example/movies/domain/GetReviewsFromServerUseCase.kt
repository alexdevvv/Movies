package com.example.movies.domain

import com.example.movies.domain.models.ReviewsResponse
import io.reactivex.Single

class GetReviewsFromServerUseCase(private val repository: NetworkRepository) {
    fun getReviews(filmId: Int): Single<ReviewsResponse> =
        repository.getReviews(filmId)
}