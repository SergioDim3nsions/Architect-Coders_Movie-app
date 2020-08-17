package com.dim3nsions.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.network.model.MoviePreview
import com.dim3nsions.movieapp.network.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val moviesRepository: MoviesRepository = MoviesRepository.instance) :
    ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _nowPlaying = MutableLiveData<List<MoviePreview>>()
    val nowPlaying: LiveData<List<MoviePreview>>
        get() = _nowPlaying

    init {
        getNowPlaying()
    }

    fun getNowPlaying() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getNowPlaying() }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }

    fun getPopular() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getPopular() }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }

    fun getUpcoming() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getUpcoming() }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }

    fun getTopRated() {
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getTopRated() }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }

    fun getSearchResults(query: String) {
        if (query.isEmpty()) return
        viewModelScope.launch {
            isLoading.value = true
            val response = withContext(Dispatchers.IO) { moviesRepository.getSearchResults(query) }
            _nowPlaying.postValue(response.results)
            isLoading.value = false
        }
    }
}