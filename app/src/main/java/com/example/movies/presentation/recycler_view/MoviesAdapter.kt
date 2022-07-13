package com.example.movies.presentation.recycler_view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.movies.R
import com.example.movies.domain.models.Movie

class MoviesAdapter : RecyclerView.Adapter<MovieViewHolder>() {
    private var listMovies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        Glide
            .with(holder.itemView)
            .load(movie.poster.url)
            .apply(
                RequestOptions()
                    .placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round)
            )
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.moviePoster)

        holder.movieRating.text = movie.rating.kp.toString()

    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    fun initListMovie(list: List<Movie>) {
        this.listMovies = list
        notifyDataSetChanged()
    }
}