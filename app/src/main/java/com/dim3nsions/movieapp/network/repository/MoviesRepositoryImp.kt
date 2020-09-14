package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.MovieAppApplication
import com.dim3nsions.movieapp.db.MovieDataBase
import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.network.model.PaginatedResponse
import com.dim3nsions.movieapp.network.model.ServerCredit
import com.dim3nsions.movieapp.network.model.ServerMovieDetail
import com.dim3nsions.movieapp.network.model.ServerMoviePreview

interface MoviesRepository {

    companion object {
        val instance by lazy {
            MoviesRepositoryImp()
        }
    }

    suspend fun getDetails(movieId: Int): ServerMovieDetail
    suspend fun getCredit(movieId: Int): ServerCredit
    suspend fun getRecommendations(movieId: Int): PaginatedResponse<ServerMoviePreview>
}

class MoviesRepositoryImp(
    private val restManager: RestManager = RestManager,
    private val movieDB: MovieDataBase = MovieAppApplication.movieDB
) : MoviesRepository {

    override suspend fun getRecommendations(movieId: Int): PaginatedResponse<ServerMoviePreview> =
        restManager.service.getRecommendations(movieId)

    override suspend fun getDetails(movieId: Int): ServerMovieDetail =
        restManager.service.getDetails(movieId)

    override suspend fun getCredit(movieId: Int): ServerCredit =
        restManager.service.getCredit(movieId)


}