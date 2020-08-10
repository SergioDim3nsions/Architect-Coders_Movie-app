package com.dim3nsions.movieapp.ui.detail.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.network.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(private val moviesRepository: MoviesRepository = MoviesRepository.instance) :
    ViewModel() {

    fun getDetails(movieId: Int) {
        viewModelScope.launch {
            val response1 = withContext(Dispatchers.IO) { moviesRepository.getDetails(movieId) }
            val response2 = withContext(Dispatchers.IO) { moviesRepository.getCredit(movieId) }
            val response3 = withContext(Dispatchers.IO) { moviesRepository.getRecommendations(movieId) }
            response1.toString()
        }
    }
}