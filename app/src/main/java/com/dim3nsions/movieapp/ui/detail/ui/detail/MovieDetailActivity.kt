package com.dim3nsions.movieapp.ui.detail.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dim3nsions.movieapp.R

class MovieDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_detail_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MovieDetailFragment.newInstance())
                .commitNow()
        }
    }
}