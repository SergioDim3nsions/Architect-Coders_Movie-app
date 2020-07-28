package com.dim3nsions.movieapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.ui.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val nowPlaying = MutableLiveData<List<Movie>>()

    fun getMovies() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { RestManager.service.getNowPlaying() }
            nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }
}