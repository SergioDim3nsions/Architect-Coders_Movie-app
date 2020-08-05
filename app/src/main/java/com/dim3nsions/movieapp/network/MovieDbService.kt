package com.dim3nsions.movieapp.network

import com.dim3nsions.movieapp.API_KEY
import com.dim3nsions.movieapp.network.model.PaginatedResponse
import com.dim3nsions.movieapp.ui.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    interface Movies {
        companion object {
            const val NOW_PLAYING = "movie/now_playing"
            const val POPULAR = "movie/popular"
            const val UPCOMING = "movie/upcoming"
            const val TOP_RATED = "movie/top_rated"
        }
    }
}

interface MovieDbService {

    @GET(Endpoints.Movies.NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<Movie>

    @GET(Endpoints.Movies.POPULAR)
    suspend fun getPopular(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<Movie>

    @GET(Endpoints.Movies.UPCOMING)
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<Movie>

    @GET(Endpoints.Movies.TOP_RATED)
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<Movie>
}