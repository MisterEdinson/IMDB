package com.example.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb.data.local.model.HomeMovieModel

@Dao
interface HomeMovieDao {
    @Query("SELECT * FROM home_movie WHERE title LIKE :search")
    suspend fun getHomeMovie(search: String): HomeMovieModel
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeMovie(insert: HomeMovieModel)
}