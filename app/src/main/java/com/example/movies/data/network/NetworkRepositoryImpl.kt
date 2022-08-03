package com.example.movies.data.network

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.NetworkRepository
import com.example.movies.domain.models.ReviewsResponse
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single

class NetworkRepositoryImpl(val apiService: ApiService): NetworkRepository {

    override fun getTopMovie(page: Int): Single<MovieResponse> =
        apiService.getTopMovies(page)

    override fun getTrailerForMovie(id: Int): Single<TrailerResponse> =
        apiService.getTrailerForMovie(id)

    override fun getReviews(filmId: Int): Single<ReviewsResponse> =
        apiService.getReviewById(id = filmId)

}