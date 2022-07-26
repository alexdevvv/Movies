package com.example.movies.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.models.Movie
import com.example.movies.presentation.recyclers_view.MoviesAdapter
import java.io.Serializable

class MainActivity : AppCompatActivity(),
    MoviesAdapter.ReachEndListListener,
    MoviesAdapter.OnMovieCkickListener {
    private lateinit var viewModel: MainViewModel
    private lateinit var liveData: LiveData<MutableList<Movie>>
    private lateinit var progressBar: ProgressBar
    private lateinit var myAdapter: MoviesAdapter
    private lateinit var rv: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv = findViewById(R.id.movies_rw)
        myAdapter = MoviesAdapter()
        rv.layoutManager = GridLayoutManager(this, 2)

        rv.adapter = myAdapter
        progressBar = findViewById(R.id.progress_bar)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        liveData = viewModel.getMovieLiveData()
        bindLiveData()

        viewModel.getTopMovies()

    }

    private fun bindLiveData() {
        liveData.observe(this) {
            progressBar.visibility = View.INVISIBLE
            initRecyclerView(it)
        }
    }

    private fun initRecyclerView(list: List<Movie>) {
        myAdapter.reachEndListListener = this
        myAdapter.onMovieCkickListener = this
        myAdapter.initListMovie(list)
    }

    override fun onRichEnd() {
        viewModel.getTopMovies()
    }

    override fun onMovieClick(movie: Movie) {
        val intent = Intent(this, DetailMovieActivity::class.java)
        intent.putExtra(DetailMovieActivity.MOVIE_KEY, movie as Serializable)
        startActivity(intent)
    }

}