package com.example.movies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.RetrofitHelper
import com.example.movies.domain.GetReviewsUseCase
import com.example.movies.domain.GetTrailerForMovieUseCase
import com.example.movies.domain.models.Review
import com.example.movies.domain.models.Trailer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailViewModel : ViewModel() {

    private val api = RetrofitHelper.apiService
    private val repository = MoviesRepositoryImpl(apiService = api)
    private var disposable = CompositeDisposable()
    private val getTrailerForMovieUseCase = GetTrailerForMovieUseCase(repository)
    private val getReviewsUseCase = GetReviewsUseCase(repository)

    private val trailerLiveData = MutableLiveData<List<Trailer>>()
    private val reviewsLiveData = MutableLiveData<List<Review>>()

    fun getTrailerLiveData(): LiveData<List<Trailer>> = trailerLiveData
    fun getReviewsLiveData(): LiveData<List<Review>> = reviewsLiveData

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

    fun getReviewsById(id: Int){
        disposable.add(
            getReviewsUseCase.getReviews(filmId = id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> t.docs }
                .subscribe(
                    {
                        reviewsLiveData.postValue(it)
                    },{
                        Log.e("LOADING_REVIEWS_ERROR", it.message.toString())
                    }
                )
        )
    }

}