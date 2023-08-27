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

    @Query("SELECT * FROM home_movie ORDER BY raitingKp DESC")
    suspend fun getAll(): List<HomeMovieModel>

    @Query("SELECT * FROM home_movie WHERE favorite LIKE :id")
    suspend fun getAllFavorite(id:Int): List<HomeMovieModel>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHomeMovie(insert: List<HomeMovieModel>)

    @Update
    suspend fun updateMovie(movie: HomeMovieModel)

    @Query("DELETE FROM home_movie WHERE favorite = 0 AND id NOT IN (SELECT id FROM home_movie WHERE favorite = 1 ORDER BY id DESC LIMIT 30)")
    suspend fun deleteOld()
}