package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.MovieAppApplication
import com.dim3nsions.movieapp.db.MovieDataBase
import com.dim3nsions.movieapp.db.MovieSection
import com.dim3nsions.movieapp.db.convertToDbMovie
import com.dim3nsions.movieapp.db.model.DBMoviePreview
import com.dim3nsions.movieapp.network.RestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GetNowPlayingRepository {

    suspend fun getNowPlaying(): List<DBMoviePreview>
    fun resetPage()

    companion object {
        val instance: GetNowPlayingRepository by lazy {
            GetNowPlayingRepositoryImp()
        }
    }
}

class GetNowPlayingRepositoryImp(
    private val restManager: RestManager = RestManager,
    private val movieDB: MovieDataBase = MovieAppApplication.movieDB
) : GetNowPlayingRepository {

    private var currentPage = 1

    override suspend fun getNowPlaying(): List<DBMoviePreview> =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                if (nowPlayingCount(currentPage) == 0) {
                    val response = restManager.service.getNowPlaying(page = currentPage)
                    val dbMovieList = response.results.map { movie ->
                        movie.convertToDbMovie(
                            MovieSection.NOW_PAYING.sectionId,
                            currentPage
                        )
                    }

                    insertMovies(dbMovieList)
                }

                val movies = movieDB.movieDao().getNowPlaying(currentPage)
                currentPage += 1
                movies
            }
        }

    override fun resetPage() {
        currentPage = 1
    }
}