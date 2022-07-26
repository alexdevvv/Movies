package com.example.movies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.RetrofitHelper
import com.example.movies.domain.GetTrailerForMovieUseCase
import com.example.movies.domain.models.Trailer
import com.example.movies.domain.models.TrailerResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.functions.Function

class DetailViewModel : ViewModel() {

    private val api = RetrofitHelper.apiService
    private val repository = MoviesRepositoryImpl(apiService = api)
    private var disposable = CompositeDisposable()
    private val getTrailerForMovieUseCase = GetTrailerForMovieUseCase(repository)

    private val trailerLiveData = MutableLiveData<List<Trailer>>()

    fun getTrailerLiveData(): LiveData<List<Trailer>> = trailerLiveData

    fun getTrailerForMovie() {
        disposable.add(
            getTrailerForMovieUseCase.getTrailerForMovie(326)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t -> t.listTrailers.traillers }
                .subscribe(
                    {
                        trailerLiveData.postValue(it)
                    }, {
                        Log.e("YYYY", it.message.toString())
                    }
                )
        )
    }
}