package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.manager.LocationManager
import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.network.model.ServerMoviePreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*

interface GetSearchRepository {

    suspend fun getSearchResult(query: String): List<ServerMoviePreview>
    fun resetPage()

    companion object {
        val instance: GetSearchRepository by lazy {
            GetSearchRepositoryImp()
        }
    }
}

class GetSearchRepositoryImp(
    private val restManager: RestManager = RestManager,
    private val locationManager: LocationManager = LocationManager.instance
) : GetSearchRepository {

    private var currentPage = 1
    private var currentQuery = ""

    override suspend fun getSearchResult(query: String): List<ServerMoviePreview> =
        withContext(Dispatchers.IO) {
            if (query != currentQuery) {
                currentPage = 1
            }

            currentQuery = query
            val response = restManager.service.getSearchResults(
                page = currentPage,
                query = query,
                region = locationManager.findLastRegion(),
                language = Locale.getDefault().toLanguageTag()
            )
            currentPage += 1
            response.results
        }

    override fun resetPage() {
        currentPage = 1
    }
}