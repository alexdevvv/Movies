package com.example.movies.presentation.recyclers_view

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R

class ReviewViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val reviewText = itemView.findViewById<TextView>(R.id.review_tv)
    val authorName = itemView.findViewById<TextView>(R.id.author_tv)
    val containerReviewData = itemView.findViewById<LinearLayout>(R.id.container_review_data_ll)
}