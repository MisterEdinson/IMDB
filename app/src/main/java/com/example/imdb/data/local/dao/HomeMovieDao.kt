package com.example.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.imdb.data.local.model.HomeMovieModel

@Dao
interface HomeMovieDao {
    @Query("SELECT * FROM home_movie WHERE title LIKE :search")
    suspend fun getHomeMovie(search: String): HomeMovieModel

    @Query("SELECT * FROM home_movie WHERE title LIKE '%' || :search || '%' AND favorite = 1")
    suspend fun searchLocalFavorite(search: String): List<HomeMovieModel>

    @Query("SELECT * FROM home_movie WHERE favorite = 0 ORDER BY raitingKp DESC")
    suspend fun getAll(): List<HomeMovieModel>

    @Query("SELECT * FROM home_movie WHERE favorite = 1")
    suspend fun getAllFavorite(): List<HomeMovieModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHomeMovie(insert: List<HomeMovieModel>)

    @Query("SELECT COUNT(*) FROM home_movie WHERE favorite LIKE :favorite")
    suspend fun getcountFavorite(favorite:Int): Int

    @Query("SELECT COUNT(*) FROM home_movie WHERE favorite = 0")
    suspend fun getmoviesDefault(): Int

    @Update
    suspend fun updateMovie(movie: HomeMovieModel)

    @Query("DELETE FROM home_movie WHERE favorite = 0")
    suspend fun deleteOld()

    @Query("DELETE FROM home_movie WHERE favorite = 1")
    suspend fun deleteAllFavorite()
}