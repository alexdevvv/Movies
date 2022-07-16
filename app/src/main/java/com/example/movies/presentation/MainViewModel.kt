package com.example.movies.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.RetrofitHelper
import com.example.movies.domain.GetTopMoviesUseCase
import com.example.movies.domain.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private var disposable = CompositeDisposable()
    private val api = RetrofitHelper.apiService
    private val repository = MoviesRepositoryImpl(apiService = api)
    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)
    private val moviesLiveData = MutableLiveData<MutableList<Movie>>()
    private var page = 1
    var list = mutableListOf<Movie>()

    fun getMovieLiveData(): LiveData<MutableList<Movie>> {
        return moviesLiveData
    }

    fun getTopMovies() {
        disposable.addAll(
            getTopMoviesUseCase.getTopMovie(page = page)
                .subscribeOn(Schedulers.io()) //  Указываем в каком потоке получать данные
                .observeOn(AndroidSchedulers.mainThread()) // Указываем что выводыить данные мы будем в основном програмном потоке
                .subscribe({movieResponse ->
                    moviesLiveData.value?.let {
                        list.addAll(movieResponse.movies)
                        Log.e("XXX", list.size.toString())
                        moviesLiveData.value = list
                    }
                    if (moviesLiveData.value == null){
                        moviesLiveData.value = movieResponse.movies as MutableList<Movie>
                    }
                    page++

                }, {

                })
        )
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }
}
