package com.example.movies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.MainViewModel
import com.example.movies.presentation.recycler_view.MoviesAdapter

class MainActivity : AppCompatActivity(), MoviesAdapter.ReachEndListListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var liveData: LiveData<MutableList<Movie>>
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        liveData = viewModel.getMovieLiveData()
        bindLiveData()
        viewModel.getTopMovies()

    }

    private fun bindLiveData(){
        liveData.observe(this){
            progressBar.visibility = View.INVISIBLE
            initRecyclerView(it)

        }
    }
    
    private fun initRecyclerView(list: List<Movie>){
        val rv = findViewById<RecyclerView>(R.id.movies_rw)
        val adapter = MoviesAdapter()
        adapter.reachEndListListener = this
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(this, 2)
        adapter.initListMovie(list)

    }

    override fun onRichEnd() {
        viewModel.getTopMovies()
        Toast.makeText(this, "Конец списка", Toast.LENGTH_LONG).show()
    }
}