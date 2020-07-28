package com.dim3nsions.movieapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dim3nsions.movieapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }


    }
}