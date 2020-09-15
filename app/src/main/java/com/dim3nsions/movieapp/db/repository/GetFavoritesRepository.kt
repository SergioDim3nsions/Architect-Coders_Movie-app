package com.dim3nsions.movieapp.db.repository

import com.dim3nsions.movieapp.MovieAppApplication
import com.dim3nsions.movieapp.db.MovieDataBase
import com.dim3nsions.movieapp.db.model.DBMoviePreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GetFavoritesRepository {

    suspend fun getFavorites(): List<DBMoviePreview>
    suspend fun checkIfMovieIsFavorite(movieId: Int): Boolean
    suspend fun updateFavorite(movieId: Int): Boolean

    companion object {
        val instance: GetFavoritesRepository by lazy {
            GetFavoritesRepositoryImp()
        }
    }
}

class GetFavoritesRepositoryImp(
    private val movieDB: MovieDataBase = MovieAppApplication.movieDB
) : GetFavoritesRepository {

    override suspend fun getFavorites(): List<DBMoviePreview> =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                getAllFavorites()
            }
        }

    override suspend fun checkIfMovieIsFavorite(movieId: Int): Boolean =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                getFavoritesCountByMovieId(movieId) != 0
            }
        }

    override suspend fun updateFavorite(movieId: Int): Boolean =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                val dbMovie = getMovieById(movieId)
                dbMovie.isFavorite = !dbMovie.isFavorite
                updateMovie(dbMovie)
                dbMovie.isFavorite
            }
        }
}