package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.network.model.PaginatedResponse
import com.dim3nsions.movieapp.ui.model.Movie

interface MoviesRepository {

    companion object {
        val instance by lazy {
            MoviesRepositoryImp()
        }
    }

    suspend fun getNowPlaying(): PaginatedResponse<Movie>
}

class MoviesRepositoryImp(private val restManager: RestManager = RestManager) :
    MoviesRepository {

    override suspend fun getNowPlaying(): PaginatedResponse<Movie> =
        restManager.service.getNowPlaying()
}