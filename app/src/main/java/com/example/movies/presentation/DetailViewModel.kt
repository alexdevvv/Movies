package com.example.movies.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movies.data.db.DbRepositoryImpl
import com.example.movies.data.db.MovieDao
import com.example.movies.data.db.MovieDatabase
import com.example.movies.data.network.NetworkRepositoryImpl
import com.example.movies.data.network.RetrofitHelper
import com.example.movies.domain.*
import com.example.movies.domain.models.Movie
import com.example.movies.domain.models.Review
import com.example.movies.domain.models.Trailer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel(application: Application) : AndroidViewModel(application) {

    private var dao: MovieDao

    init {
        dao = MovieDatabase.getDbInstance(application).getMoviedao()
    }

    private val api = RetrofitHelper.apiService
    private val netWorkRepository = NetworkRepositoryImpl(apiService = api)
    private val dbReposytory = DbRepositoryImpl(dao)
    private var disposable = CompositeDisposable()
    private val getTrailerForMovieUseCase = GetTrailerFromServerUseCase(netWorkRepository)
    private val getReviewsUseCase = GetReviewsFromServerUseCase(netWorkRepository)
    private val inserMovieInDbUseCase = InserMovieInDbUseCase(dbReposytory)
    private val getMovieByIdFromDbUseCase = GetMovieByIdFromDbUseCase(dbReposytory)
    private val deleteMovieByIdFromDbUseCase = DeleteMovieFromBdUseCase(dbReposytory)

    private val trailerLiveData = MutableLiveData<List<Trailer>>()
    private val reviewsLiveData = MutableLiveData<List<Review>>()

    private val isSuccesInserDbLivedata = MutableLiveData<String>()

    fun getTrailerLiveData(): LiveData<List<Trailer>> = trailerLiveData
    fun getReviewsLiveData(): LiveData<List<Review>> = reviewsLiveData

    fun getIsSuccesInserDbLivedata(): LiveData<String> {
        return isSuccesInserDbLivedata
    }

    fun getTrailerForMovie(filmId: Int) {
        disposable.add(
            getTrailerForMovieUseCase.getTrailerForMovie(id = filmId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> t.listTrailers.traillers }
                .subscribe(
                    {
                        trailerLiveData.postValue(it)
                    }, {
                        Log.e("LOADING_TRAILER_ERROR", it.toString())
                    }
                )
        )
    }

    fun getReviewsById(id: Int) {
        disposable.add(
            getReviewsUseCase.getReviews(filmId = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> t.docs }
                .subscribe(
                    {
                        reviewsLiveData.postValue(it)
                    }, {
                        Log.e("LOADING_REVIEWS_ERROR", it.message.toString())
                    }
                )
        )
    }

    fun insertMovieInDb(movie: Movie) {
        disposable.add(
            inserMovieInDbUseCase.inserMovie(movie)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun deleteMovie(movieId: Int){
        disposable.add(
            deleteMovieByIdFromDbUseCase.deleteMovieById(movieId)
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun getMovieFromDbById(id: Int): LiveData<Movie> {
       return getMovieByIdFromDbUseCase.getMovieById(id)
    }


    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }


}