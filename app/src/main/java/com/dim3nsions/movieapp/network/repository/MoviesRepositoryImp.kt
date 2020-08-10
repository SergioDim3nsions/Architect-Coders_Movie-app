package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.network.model.Credit
import com.dim3nsions.movieapp.network.model.MovieDetail
import com.dim3nsions.movieapp.network.model.MoviePreview
import com.dim3nsions.movieapp.network.model.PaginatedResponse

interface MoviesRepository {

    companion object {
        val instance by lazy {
            MoviesRepositoryImp()
        }
    }

    suspend fun getNowPlaying(): PaginatedResponse<MoviePreview>
    suspend fun getPopular(): PaginatedResponse<MoviePreview>
    suspend fun getUpcoming(): PaginatedResponse<MoviePreview>
    suspend fun getTopRated(): PaginatedResponse<MoviePreview>
    suspend fun getDetails(movieId: Int): MovieDetail
    suspend fun getCredit(movieId: Int): Credit
    suspend fun getRecommendations(movieId: Int): PaginatedResponse<MoviePreview>
}

class MoviesRepositoryImp(private val restManager: RestManager = RestManager) :
    MoviesRepository {

    override suspend fun getNowPlaying(): PaginatedResponse<MoviePreview> =
        restManager.service.getNowPlaying()

    override suspend fun getPopular(): PaginatedResponse<MoviePreview> =
        restManager.service.getPopular()

    override suspend fun getUpcoming(): PaginatedResponse<MoviePreview> =
        restManager.service.getUpcoming()

    override suspend fun getTopRated(): PaginatedResponse<MoviePreview> =
        restManager.service.getTopRated()

    override suspend fun getDetails(movieId: Int): MovieDetail =
        restManager.service.getDetails(movieId)

    override suspend fun getCredit(movieId: Int): Credit =
        restManager.service.getCredit(movieId)

    override suspend fun getRecommendations(movieId: Int): PaginatedResponse<MoviePreview> =
        restManager.service.getRecommendations(movieId)
}