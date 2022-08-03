package com.example.movies.data.network

import com.example.movies.domain.models.MovieResponse
import com.example.movies.domain.models.ReviewsResponse
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movie?token=VSDXK3E-6614SGM-K3PVWSA-JBEF9HJ&field=rating.kp&search=8.0-10.0&limit=30&field=year&search=2017-2020&")
    fun getTopMovies(@Query("page")page: Int): Single<MovieResponse>

    @GET("/movie?token=VSDXK3E-6614SGM-K3PVWSA-JBEF9HJ&field=id")
    fun getTrailerForMovie(@Query("search") id: Int): Single<TrailerResponse>

    @GET("/review?token=ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06&field=movieId")
    fun getReviewById(@Query("search") id: Int): Single<ReviewsResponse>
}