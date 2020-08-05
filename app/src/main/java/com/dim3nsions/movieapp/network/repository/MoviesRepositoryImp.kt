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
    suspend fun getPopular(): PaginatedResponse<Movie>
    suspend fun getUpcoming(): PaginatedResponse<Movie>
    suspend fun getTopRated(): PaginatedResponse<Movie>
}

class MoviesRepositoryImp(private val restManager: RestManager = RestManager) :
    MoviesRepository {

    override suspend fun getNowPlaying(): PaginatedResponse<Movie> =
        restManager.service.getNowPlaying()

    override suspend fun getPopular(): PaginatedResponse<Movie> =
        restManager.service.getPopular()

    override suspend fun getUpcoming(): PaginatedResponse<Movie> =
        restManager.service.getUpcoming()

    override suspend fun getTopRated(): PaginatedResponse<Movie> =
        restManager.service.getTopRated()
}