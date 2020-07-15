package com.dim3nsions.movieapp.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.inflate
import com.dim3nsions.movieapp.ui.model.Movie

class MovieAdapter(private val items: List<Movie> = mutableListOf()) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(parent.inflate(R.layout.item_movie))

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.displayItems(items[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

       fun displayItems(movie: Movie){

       }
    }
}