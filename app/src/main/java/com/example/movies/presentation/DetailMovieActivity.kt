package com.example.movies.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.domain.models.Movie

class DetailMovieActivity : AppCompatActivity() {
    private lateinit var filmPoster: ImageView
    private lateinit var filmName: TextView
    private lateinit var filmYear: TextView
    private lateinit var filmDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)
        initViews()
        val movie = getMovieIntent()
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

    companion object{
        const val MOVIE_KEY = "movie"
    }

    private fun getMovieIntent(): Movie{
        val intent = intent
        val movie = intent.extras?.getSerializable(MOVIE_KEY)
        return movie as Movie
    }

    private fun initViews(){
        filmPoster = findViewById(R.id.detail_movie_poster_iv)
        filmName = findViewById(R.id.film_name_tv)
        filmYear = findViewById(R.id.film_year_tv)
        filmDescription = findViewById(R.id.film_description_tv)
    }




}