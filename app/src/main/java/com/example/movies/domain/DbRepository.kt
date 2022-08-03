package com.example.movies.domain

import androidx.lifecycle.LiveData
import com.example.movies.domain.models.Movie
import io.reactivex.Completable

interface DbRepository {

    fun getMovieById(id: Int): LiveData<Movie>

    fun getAllFavoriteMovies(): LiveData<List<Movie>>

    fun insertMovie(movie: Movie): Completable

    fun removeMovie(id: Int): Completable

}