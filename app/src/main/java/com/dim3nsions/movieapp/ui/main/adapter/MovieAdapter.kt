package com.dim3nsions.movieapp.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.databinding.ViewMovieItemBinding
import com.dim3nsions.movieapp.inflate
import com.dim3nsions.movieapp.loadUrl
import com.dim3nsions.movieapp.network.model.MoviePreview
import kotlin.properties.Delegates

typealias MovieAdapterListener = (MoviePreview) -> Unit

class MovieAdapter(private val listener: MovieAdapterListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var items: List<MoviePreview> by Delegates.observable(emptyList()) { _, old, new ->
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

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = items[position]
        holder.displayItems(movie)
        holder.itemView.setOnClickListener { listener(movie) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewMovieItemBinding.bind(view)

        fun displayItems(movie: MoviePreview) {
            binding.tvTitle.text = movie.title
            binding.ivMovie.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
        }
    }

    fun clear() {
        items = listOf()
    }
}