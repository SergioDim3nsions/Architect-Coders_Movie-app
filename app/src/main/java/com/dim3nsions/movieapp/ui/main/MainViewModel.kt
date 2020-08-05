package com.dim3nsions.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.network.repository.MoviesRepository
import com.dim3nsions.movieapp.ui.model.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val moviesRepository: MoviesRepository = MoviesRepository.instance) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _nowPlaying = MutableLiveData<List<Movie>>()
    val nowPlaying: LiveData<List<Movie>>
        get() = _nowPlaying

    init {
        getNowPlaying()
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getNowPlaying() }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }
}