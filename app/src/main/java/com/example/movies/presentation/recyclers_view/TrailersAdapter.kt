package com.example.movies.presentation.recyclers_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movies.R
import com.example.movies.domain.models.Trailer

class TrailersAdapter : RecyclerView.Adapter<TrailerViewHolder>() {

    private var listTrailer = listOf<Trailer>()

    var onClickTreilerListener: OnClickTrailerListener? = null
    set(value) {
        field = value
    }

    interface OnClickTrailerListener {
        fun onClickTrailer(trailer: Trailer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.trailer_item, parent, false)
        return TrailerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrailerViewHolder, position: Int) {
        var trailer = listTrailer[position]
        holder.trailerName.text = trailer.name
        holder.itemView.setOnClickListener {
            onClickTreilerListener?.onClickTrailer(trailer)
        }
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }

    fun initListTrailer(list: List<Trailer>) {
        this.listTrailer = list
        notifyDataSetChanged()
    }


}