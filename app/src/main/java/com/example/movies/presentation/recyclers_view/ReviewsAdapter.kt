package com.example.movies.presentation.recyclers_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.models.Review

const val REVIEW_POSITIVE = "Позитивный"
const val REVIEW_NEGATIVE = "Негативный"

class ReviewsAdapter : RecyclerView.Adapter<ReviewViewHolder>() {

    var listReviews = listOf<Review>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.review_item,
            parent,
            false
        )
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = listReviews[position]
        holder.reviewText.text = review.review
        holder.authorName.text = review.author

        val backGroundColor = initBackgroundForReview(review.type)
        val drawBackground = ContextCompat.getDrawable(holder.itemView.context, backGroundColor)
        holder.containerReviewData.background = drawBackground
    }

    override fun getItemCount(): Int {
        return listReviews.size
    }

    private fun initBackgroundForReview(typeReview: String): Int {
        return when (typeReview) {
            REVIEW_POSITIVE -> android.R.color.holo_green_light
            REVIEW_NEGATIVE -> android.R.color.holo_red_light
            else -> android.R.color.holo_orange_light
        }

    }

    fun initListReview(list: List<Review>) {
        listReviews = list
        notifyDataSetChanged()
    }
}