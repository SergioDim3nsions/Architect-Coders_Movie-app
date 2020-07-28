package com.dim3nsions.movieapp.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.databinding.ViewMovieItemBinding
import com.dim3nsions.movieapp.inflate
import com.dim3nsions.movieapp.ui.model.Movie

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val items: MutableList<Movie> = mutableListOf()

    fun addItems(items: List<Movie>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.view_movie_item))

    override fun getItemCount() = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.displayItems(items[position])

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        
        private val binding = ViewMovieItemBinding.bind(view)

        fun displayItems(movie: Movie) {
            binding.title.text = movie.title
        }
    }
}