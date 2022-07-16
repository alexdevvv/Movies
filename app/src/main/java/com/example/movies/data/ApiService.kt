package com.example.movies.data

import com.example.movies.domain.models.MovieResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movie?token=VSDXK3E-6614SGM-K3PVWSA-JBEF9HJ&field=rating.kp&search=7.0-10.0&limit=30&field=year&search=2017-2020&sortField=votes.imdb&sortType=1")
    fun getTopMovies(@Query("page")page: Int): Single<MovieResponse>
}