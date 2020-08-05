package com.dim3nsions.movieapp.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.databinding.ViewMovieItemBinding
import com.dim3nsions.movieapp.inflate
import com.dim3nsions.movieapp.ui.model.Movie
import kotlin.properties.Delegates

class MovieAdapter() :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var items: List<Movie> by Delegates.observable(emptyList()) { _, old, new ->
        DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                old[oldItemPosition].id == new[newItemPosition].id

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                old[oldItemPosition] == new[newItemPosition]

            override fun getOldListSize() = old.size
            override fun getNewListSize() = new.size
        }).dispatchUpdatesTo(this)
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