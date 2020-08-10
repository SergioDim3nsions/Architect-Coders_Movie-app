package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dim3nsions.movieapp.R
import com.dim3nsions.movieapp.network.model.MoviePreview

class MovieDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)
        initFragment()
    }

    private fun initFragment() {
        val movie = intent.extras?.getParcelable<MoviePreview>(EXTRA_MOVIE)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MovieDetailFragment.newInstance(movie))
            .commitNow()
    }

}