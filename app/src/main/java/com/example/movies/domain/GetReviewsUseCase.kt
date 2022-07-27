package com.example.movies.domain

import com.example.movies.domain.models.ReviewsResponse
import io.reactivex.Single

class GetReviewsUseCase(val repository: MoviesRepository) {
    fun getReviews(filmId: Int): Single<ReviewsResponse> =
        repository.getReviews(filmId)
}