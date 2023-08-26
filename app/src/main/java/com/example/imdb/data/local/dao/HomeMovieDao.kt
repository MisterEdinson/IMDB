package com.example.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb.data.local.model.HomeMovieModel

@Dao
interface HomeMovieDao {
    @Query("SELECT * FROM home_movie WHERE title LIKE :search")
    suspend fun getHomeMovie(search: String): HomeMovieModel

    @Query("SELECT * FROM home_movie")
    suspend fun getAllFavorite(): List<HomeMovieModel>

    @Query("SELECT * FROM home_movie WHERE idkp LIKE :id")
    suspend fun searchFavoriteId(id:String): HomeMovieModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHomeMovie(insert: HomeMovieModel)

    @Delete
    suspend fun delFavorite(favorite: HomeMovieModel)
}