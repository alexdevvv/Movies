package com.example.movies.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movies.data.MoviesRepositoryImpl
import com.example.movies.data.RetrofitHelper
import com.example.movies.domain.GetTopMoviesUseCase
import com.example.movies.domain.models.Movie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    private var disposable = CompositeDisposable()
    private val api = RetrofitHelper.apiService
    private val repository = MoviesRepositoryImpl(apiService = api)
    private val getTopMoviesUseCase = GetTopMoviesUseCase(repository)
    private val moviesLiveData = MutableLiveData<List<Movie>>()

    private var page = 1

    fun getMovieLiveData(): LiveData<List<Movie>> {
        return moviesLiveData
    }

    fun getTopMovies() {
        getTopMoviesUseCase.getTopMovie(page = page)
            .subscribeOn(Schedulers.io()) //  Указываем в каком потоке получать данные
            .observeOn(AndroidSchedulers.mainThread()) // Указываем что выводыить данные мы будем в основном програмном потоке
            .subscribe({
                page++
                moviesLiveData.postValue(it.movies)
            }, {

            })

    }
}
