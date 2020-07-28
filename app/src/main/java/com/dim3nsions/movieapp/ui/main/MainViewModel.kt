package com.dim3nsions.movieapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.network.RestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    fun getMovies() {
        viewModelScope.launch {
            val response =
                withContext(Dispatchers.IO) { RestManager.service.getNowPlaying() }
            response.toString()
        }
    }
}