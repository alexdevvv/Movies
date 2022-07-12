package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var liveData: LiveData<List<Movie>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        liveData = viewModel.getMovieLiveData()
        bindLiveData()
        viewModel.getTopMovies()

    }

    private fun bindLiveData(){
        liveData.observe(this){
            Log.e("RESULT_OBSERVE", it.toString())
        }
    }
}