package com.example.movies.data

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.models.MoviesRepository
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single

class MoviesRepositoryImpl(val apiService: ApiService): MoviesRepository {

    override fun getTopMovie(page: Int): Single<MovieResponse> =
        apiService.getTopMovies(page)

    override fun getTrailerForMovie(id: Int): Single<TrailerResponse> =
        apiService.getTrailerForMovie(id)

}