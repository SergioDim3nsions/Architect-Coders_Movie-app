package com.dim3nsions.movieapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dim3nsions.movieapp.db.convertToPresentationMovie
import com.dim3nsions.movieapp.db.model.DBMoviePreview
import com.dim3nsions.movieapp.network.model.ServerMoviePreview
import com.dim3nsions.movieapp.network.repository.*
import com.dim3nsions.movieapp.ui.model.PresentationMoviePreview
import kotlinx.coroutines.launch

class MainViewModel(
    private val moviesRepository: MoviesRepository = MoviesRepository.instance,
    private val getNowPlayingRepository: GetNowPlayingRepository = GetNowPlayingRepository.instance,
    private val getPopularRepository: GetPopularRepository = GetPopularRepository.instance,
    private val getUpcomingRepository: GetUpcomingRepository = GetUpcomingRepository.instance,
    private val getTopRatedRepository: GetTopRatedRepository = GetTopRatedRepository.instance,
    private val getSearchResultRepository: GetSearchRepository = GetSearchRepository.instance
) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()

    private val _movies = MutableLiveData<List<PresentationMoviePreview>>()
    val movies: LiveData<List<PresentationMoviePreview>>
        get() = _movies

    init {
        getNowPlaying()
    }

    fun resetPages() {
        getNowPlayingRepository.resetPage()
        getPopularRepository.resetPage()
        getUpcomingRepository.resetPage()
        getTopRatedRepository.resetPage()
        getSearchResultRepository.resetPage()
    }

    fun getNowPlaying() {
        if (isLoading.value == true) return

        viewModelScope.launch {
            isLoading.value = true
            _movies.value = getNowPlayingRepository.getNowPlaying()
                .map(DBMoviePreview::convertToPresentationMovie)
            isLoading.value = false
        }
    }

    fun getPopular() {
        if (isLoading.value == true) return

        viewModelScope.launch {
            isLoading.value = true
            _movies.value = getPopularRepository.getPopular()
                .map(DBMoviePreview::convertToPresentationMovie)
            isLoading.value = false
        }
    }

    fun getUpcoming() {
        if (isLoading.value == true) return

        viewModelScope.launch {
            isLoading.value = true
            _movies.value = getUpcomingRepository.getUpcoming()
                .map(DBMoviePreview::convertToPresentationMovie)
            isLoading.value = false
        }
    }

    fun getTopRated() {
        if (isLoading.value == true) return

        viewModelScope.launch {
            isLoading.value = true
            _movies.value = getTopRatedRepository.getTopRated()
                .map(DBMoviePreview::convertToPresentationMovie)
            isLoading.value = false
        }
    }

    fun getSearchResults(query: String) {
        if (query.isEmpty()) return

        viewModelScope.launch {
            isLoading.value = true
            _movies.value = getSearchResultRepository.getSearchResult(query)
                .map(ServerMoviePreview::convertToPresentationMovie)
            isLoading.value = false
        }
    }
}