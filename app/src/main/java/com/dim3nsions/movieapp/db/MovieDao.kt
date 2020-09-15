package com.dim3nsions.movieapp.db

import androidx.room.*
import com.dim3nsions.movieapp.db.model.DBMoviePreview

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<DBMoviePreview>)

    @Update
    fun updateMovie(movie: DBMoviePreview)

    @Query("select * from DBMoviePreview")
    fun getAllMovies(): List<DBMoviePreview>

    @Query("SELECT * FROM DBMoviePreview WHERE id=:movieId ")
    fun getMovieById(movieId: Int): DBMoviePreview

    @Query("select * from DBMoviePreview WHERE movieSection = 0 and page = :page")
    fun getNowPlaying(page: Int): List<DBMoviePreview>

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE movieSection = 0 and page = :page")
    fun nowPlayingCount(page: Int): Int

    @Query("select * from DBMoviePreview WHERE movieSection = 1 and page = :page")
    fun getPopular(page: Int): List<DBMoviePreview>

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE movieSection = 1 and page = :page")
    fun popularCount(page: Int): Int

    @Query("select * from DBMoviePreview WHERE movieSection = 2 and page = :page")
    fun getUpcoming(page: Int): List<DBMoviePreview>

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE movieSection = 2 and page = :page")
    fun upcomingCount(page: Int): Int

    @Query("select * from DBMoviePreview WHERE movieSection = 3 and page = :page")
    fun getTopRated(page: Int): List<DBMoviePreview>

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE movieSection = 3 and page = :page")
    fun topRatedCount(page: Int): Int

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE isFavorite = 1")
    fun getFavoritesCount(): Int

    @Query("SELECT COUNT(id) FROM DBMoviePreview WHERE isFavorite = 1 and id=:movieId")
    fun getFavoritesCountByMovieId(movieId: Int): Int

    @Query("select * from DBMoviePreview WHERE isFavorite = 1")
    fun getAllFavorites(): List<DBMoviePreview>
}

enum class MovieSection(val sectionId: Int) {
    NOW_PAYING(0), POPULAR(1), UPCOMING(2), TOP_RATED(3)
}
