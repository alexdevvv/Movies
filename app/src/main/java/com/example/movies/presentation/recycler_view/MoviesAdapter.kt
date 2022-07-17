package com.example.movies.presentation.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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

    var reachEndListListener: ReachEndListListener? = null
    set(value) {
        field = value
    }

    interface ReachEndListListener{
        fun onRichEnd()
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Log.e("onBindViewHolder", "1")
        val movie = listMovies[position]
        Glide
            .with(holder.itemView)
            .load(movie.poster.url)
            .apply(
                RequestOptions()
            )
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.moviePoster)

        val rating = movie.rating.kp.toString().toDouble()
        val idRatingBackGround = initColorForBackgroundRating(rating)
        val drawBackground = ContextCompat.getDrawable(holder.itemView.context, idRatingBackGround)
        holder.movieRating.background = drawBackground
        holder.movieRating.text = movie.rating.kp.toString()

        if (reachEndListListener != null && position == listMovies.size - 10){
            reachEndListListener!!.onRichEnd()
        }
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }

    private fun initColorForBackgroundRating(rating: Double): Int {
        return if (rating < 8) {
            R.drawable.circle_red
        } else if (rating < 8.5) {
            R.drawable.circle_orange
        } else {
            R.drawable.circle_green
        }
    }

    fun initListMovie(list: List<Movie>) {
        this.listMovies = list
        notifyDataSetChanged()

    }
}