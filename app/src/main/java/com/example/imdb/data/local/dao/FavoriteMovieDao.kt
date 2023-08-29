package com.example.imdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imdb.data.local.model.FavoriteMovieModel

@Dao
interface FavoriteMovieDao {
    @Query("SELECT * FROM favorite_movie WHERE idkp LIKE :idkp LIMIT 1")
    suspend fun getItem(idkp: String): FavoriteMovieModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(insert: FavoriteMovieModel)

    @Query("SELECT COUNT(*) FROM favorite_movie WHERE idkp LIKE :idkp")
    suspend fun checkLocal(idkp:String): Int

}