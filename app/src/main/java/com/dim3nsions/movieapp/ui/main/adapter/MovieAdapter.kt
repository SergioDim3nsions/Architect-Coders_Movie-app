package com.dim3nsions.movieapp.ui.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.databinding.ViewMovieItemBinding
import com.dim3nsions.movieapp.inflate
import com.dim3nsions.movieapp.loadUrl
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview

typealias MovieAdapterListener = (PresentationMoviePreview) -> Unit

class MovieAdapter(private val listener: MovieAdapterListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val items: MutableList<PresentationMoviePreview> = mutableListOf()

    fun addItems(items: List<PresentationMoviePreview>) {
        this.items.addAll(items)
        notifyDataSetChanged()
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

        fun displayItems(movie: PresentationMoviePreview) {
            binding.ivMovie.loadUrl(movie.posterPath)
        }
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }
}