package com.example.movies.data.db

import androidx.lifecycle.LiveData
import com.example.movies.domain.DbRepository
import com.example.movies.domain.models.Movie
import io.reactivex.Completable

class DbRepositoryImpl(val dao: MovieDao): DbRepository {

    override fun getMovieById(id: Int): LiveData<Movie> =
        dao.getMovieById(id = id)

    override fun getAllFavoriteMovies(): LiveData<List<Movie>> =
        dao.getAllFavoriteMovies()

    override fun insertMovie(movie: Movie): Completable =
        dao.insertMovie(movie = movie)

    override fun removeMovie(id: Int): Completable =
        dao.removeMovie(id = id)

}