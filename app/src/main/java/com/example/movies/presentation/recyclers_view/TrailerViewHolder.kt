package com.example.movies.presentation.recyclers_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R

class TrailerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val trailerName: TextView = itemView.findViewById(R.id.trailer_name_tv)
}