package com.example.movies.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.movies.domain.models.Movie
import io.reactivex.Completable


@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE id =:id")
    fun getMovieById(id: Int): LiveData<List<Movie>>

    @Query("SELECT * FROM movie")
    fun getAllFavoriteMovies(): LiveData<List<Movie>>

    @Insert
    fun insertMovie(movie: Movie): Completable

    @Query("DELETE FROM  movie WHERE id = :id")
    fun removeMovie(id: Int): Completable

}