package com.dim3nsions.movieapp.ui.detail.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.db.repository.GetFavoritesRepository
import com.dim3nsions.movieapp.network.repository.MoviesRepository
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailViewModel(
    private val moviesRepository: MoviesRepository = MoviesRepository.instance,
    private val getFavoritesRepository: GetFavoritesRepository = GetFavoritesRepository.instance,
) : ViewModel() {

    var movie = MutableLiveData<PresentationMoviePreview?>()
    var isFavorite = MutableLiveData<Boolean>()


    fun getDetails(movieId: Int) {
        viewModelScope.launch {
            val response1 = withContext(Dispatchers.IO) { moviesRepository.getDetails(movieId) }
            val response2 = withContext(Dispatchers.IO) { moviesRepository.getCredit(movieId) }
            val response3 =
                withContext(Dispatchers.IO) { moviesRepository.getRecommendations(movieId) }
            response1.toString()
        }
    }

    fun checkIfMovieIsFavorite() {
        viewModelScope.launch {
            val movie = movie.value
            movie?.let {
                isFavorite.value = getFavoritesRepository.checkIfMovieIsFavorite(it.id)
            }
        }
    }

    fun updateFavorite() {
        viewModelScope.launch {
            val movie = movie.value
            movie?.let {
                isFavorite.value = getFavoritesRepository.updateFavorite(it.id)
            }
        }
    }
}