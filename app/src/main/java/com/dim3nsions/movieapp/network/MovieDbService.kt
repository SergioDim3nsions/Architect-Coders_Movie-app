package com.dim3nsions.movieapp.network

import com.dim3nsions.movieapp.API_KEY
import com.dim3nsions.movieapp.network.model.Credit
import com.dim3nsions.movieapp.network.model.MovieDetail
import com.dim3nsions.movieapp.network.model.MoviePreview
import com.dim3nsions.movieapp.network.model.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    interface Movies {
        companion object {
            const val NOW_PLAYING = "movie/now_playing"
            const val POPULAR = "movie/popular"
            const val UPCOMING = "movie/upcoming"
            const val TOP_RATED = "movie/top_rated"
            const val MOVIE_DETAIL = "movie/{movie_id}"
            const val MOVIE_RECOMMENDATIONS = "movie/{movie_id}/recommendations"
            const val MOVIE_CREDITS = "movie/{movie_id}/credits"
        }
    }
}

interface MovieDbService {

    @GET(Endpoints.Movies.NOW_PLAYING)
    suspend fun getNowPlaying(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<MoviePreview>

    @GET(Endpoints.Movies.POPULAR)
    suspend fun getPopular(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<MoviePreview>

    @GET(Endpoints.Movies.UPCOMING)
    suspend fun getUpcoming(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<MoviePreview>

    @GET(Endpoints.Movies.TOP_RATED)
    suspend fun getTopRated(
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<MoviePreview>

    @GET(Endpoints.Movies.MOVIE_DETAIL)
    suspend fun getDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetail

    @GET(Endpoints.Movies.MOVIE_CREDITS)
    suspend fun getCredit(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Credit

    @GET(Endpoints.Movies.MOVIE_RECOMMENDATIONS)
    suspend fun getRecommendations(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): PaginatedResponse<MoviePreview>
}