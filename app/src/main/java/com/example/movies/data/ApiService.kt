package com.example.movies.data

import com.example.movies.domain.models.MovieResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movie?token=VSDXK3E-6614SGM-K3PVWSA-JBEF9HJ&field=rating.kp&search=8-10&limit=30")
    fun getTopMovies(@Query("page")page: Int): Observable<MovieResponse>
}