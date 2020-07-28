package com.dim3nsions.movieapp.network

import com.dim3nsions.movieapp.API_KEY
import com.dim3nsions.movieapp.network.model.PaginatedResponse
import com.dim3nsions.movieapp.ui.model.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    interface Movie {
        companion object {
            const val NOW_PLAYING = "movie/now_playing"
        }
    }
}

interface MovieDbService {

    @GET(Endpoints.Movie.NOW_PLAYING)
    suspend fun getNowPlaying(@Query("api_key") apiKey: String = API_KEY): PaginatedResponse<Movie>
}