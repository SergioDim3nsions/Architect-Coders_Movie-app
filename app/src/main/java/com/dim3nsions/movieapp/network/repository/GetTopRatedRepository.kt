package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.MovieAppApplication
import com.dim3nsions.movieapp.db.MovieDataBase
import com.dim3nsions.movieapp.db.MovieSection
import com.dim3nsions.movieapp.db.convertToDbMovie
import com.dim3nsions.movieapp.db.model.DBMoviePreview
import com.dim3nsions.movieapp.manager.LocationManager
import com.dim3nsions.movieapp.network.RestManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface GetTopRatedRepository {

    suspend fun getTopRated(): List<DBMoviePreview>
    fun resetPage()

    companion object {
        val instance: GetTopRatedRepository by lazy {
            GetTopRatedRepositoryImp()
        }
    }
}

class GetTopRatedRepositoryImp(
    private val restManager: RestManager = RestManager,
    private val movieDB: MovieDataBase = MovieAppApplication.movieDB,
    private val locationManager: LocationManager = LocationManager.instance
) : GetTopRatedRepository {

    private var currentPage = 1

    override suspend fun getTopRated(): List<DBMoviePreview> =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                if (topRatedCount(currentPage) == 0) {
                    val response = restManager.service.getTopRated(
                        page = currentPage,
                        region = locationManager.findLastRegion(),
                        language = Locale.getDefault().toLanguageTag()
                    )
                    val dbMovieList = response.results.map { movie ->
                        movie.convertToDbMovie(
                            MovieSection.TOP_RATED.sectionId,
                            currentPage
                        )
                    }

                    insertMovies(dbMovieList)
                }

                val movies = movieDB.movieDao().getTopRated(currentPage)
                currentPage += 1
                movies
            }
        }

    override fun resetPage() {
        currentPage = 1
    }
}