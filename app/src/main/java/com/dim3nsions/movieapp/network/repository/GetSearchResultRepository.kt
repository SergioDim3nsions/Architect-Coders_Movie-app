package com.dim3nsions.movieapp.network.repository

import com.dim3nsions.movieapp.network.RestManager
import com.dim3nsions.movieapp.network.model.ServerMoviePreview
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

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
    private val restManager: RestManager = RestManager
) : GetSearchRepository {

    private var currentPage = 1
    private var currentQuery = ""

    override suspend fun getSearchResult(query: String): List<ServerMoviePreview> =
        withContext(Dispatchers.IO) {
            if (query != currentQuery) {
                currentPage = 1
            }

            currentQuery = query
            val response = restManager.service.getSearchResults(page = currentPage, query = query)
            currentPage += 1
            response.results
        }

    override fun resetPage() {
        currentPage = 1
    }
}