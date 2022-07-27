package com.example.movies.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.domain.models.Movie
import com.example.movies.domain.models.Trailer
import com.example.movies.presentation.recyclers_view.TrailersAdapter

class DetailMovieActivity : AppCompatActivity(), TrailersAdapter.OnClickTrailerListener {
    private lateinit var filmPoster: ImageView
    private lateinit var filmName: TextView
    private lateinit var filmYear: TextView
    private lateinit var filmDescription: TextView
    private lateinit var detailViewModel: DetailViewModel
    private lateinit var rv: RecyclerView
    private lateinit var trailersAdapter: TrailersAdapter

    companion object{
        const val MOVIE_KEY = "movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        detailViewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
        initViews()
        bindLiveData()
        initDataFilm()

    }

    private fun bindLiveData(){
        detailViewModel.getTrailerLiveData().observe(this
        ) {
            initRecyclerView(it)
        }
    }

    private fun getMovieIntent(): Movie{
        val intent = intent
        val movie = intent.extras?.getSerializable(MOVIE_KEY)
        return movie as Movie
    }

    private fun initDataFilm(){
        val movie = getMovieIntent()
        detailViewModel.getTrailerForMovie(movie.id)
        filmName.text = movie.name
        filmYear.text = movie.year.toString()
        filmDescription.text = movie.description

        Glide
            .with(this)
            .load(movie.poster.url)
            .apply(
                RequestOptions()
            )
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(filmPoster)
    }

    private fun initRecyclerView(listTrailers: List<Trailer>){
        rv = findViewById(R.id.trailers_rv)
        trailersAdapter = TrailersAdapter()
        rv.adapter = trailersAdapter
        rv.layoutManager = LinearLayoutManager(this)
        trailersAdapter.initListTrailer(listTrailers)
        trailersAdapter.onClickTreilerListener = this

    }

    private fun initViews(){
        filmPoster = findViewById(R.id.detail_movie_poster_iv)
        filmName = findViewById(R.id.film_name_tv)
        filmYear = findViewById(R.id.film_year_tv)
        filmDescription = findViewById(R.id.film_description_tv)

    }

    override fun onClickTrailer(trailer: Trailer) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(trailer.url)
        startActivity(intent)

    }

}