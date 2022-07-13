package com.example.movies.presentation.recycler_view

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R

class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {
    val moviePoster = itemView.findViewById<ImageView>(R.id.poster_iv)
    val movieRating = itemView.findViewById<TextView>(R.id.rating_tv)
}