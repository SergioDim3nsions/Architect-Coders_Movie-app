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

interface GetUpcomingRepository {

    suspend fun getUpcoming(): List<DBMoviePreview>
    fun resetPage()

    companion object {
        val instance: GetUpcomingRepository by lazy {
            GetUpcomingRepositoryImp()
        }
    }
}

class GetUpcomingRepositoryImp(
    private val restManager: RestManager = RestManager,
    private val movieDB: MovieDataBase = MovieAppApplication.movieDB,
    private val locationManager: LocationManager = LocationManager.instance
) : GetUpcomingRepository {

    private var currentPage = 1

    override suspend fun getUpcoming(): List<DBMoviePreview> =
        withContext(Dispatchers.IO) {
            with(movieDB.movieDao()) {
                if (upcomingCount(currentPage) == 0) {
                    val response = restManager.service.getUpcoming(
                        page = currentPage,
                        region = locationManager.findLastRegion(),
                        language = Locale.getDefault().toLanguageTag()
                    )
                    val dbMovieList = response.results.map { movie ->
                        movie.convertToDbMovie(
                            MovieSection.UPCOMING.sectionId,
                            currentPage
                        )
                    }

                    insertMovies(dbMovieList)
                }

                val movies = movieDB.movieDao().getUpcoming(currentPage)
                currentPage += 1
                movies
            }
        }

    override fun resetPage() {
        currentPage = 1
    }
}