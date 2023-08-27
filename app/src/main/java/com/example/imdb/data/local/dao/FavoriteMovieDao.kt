package com.example.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb.data.local.model.FavoriteMovieModel

@Dao
interface FavoriteMovieDao {
//    @Query("SELECT * FROM favorite_movie WHERE title LIKE :search")
//    suspend fun getFavorite(search: String): FavoriteMovieModel
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertFavorite(insert: FavoriteMovieModel)
//
//    @Query("SELECT * FROM favorite_movie WHERE idkp LIKE :id")
//    suspend fun searchItem(id: String): FavoriteMovieDao
}