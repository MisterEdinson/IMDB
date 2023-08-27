package com.example.imdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imdb.data.local.dao.FavoriteMovieDao
import com.example.imdb.data.local.dao.HomeMovieDao
import com.example.imdb.data.local.model.FavoriteMovieModel
import com.example.imdb.data.local.model.HomeMovieModel

@Database(entities = [
    FavoriteMovieModel::class,
    HomeMovieModel::class],
    version = 1,
    exportSchema = false)
abstract class ImdbDao : RoomDatabase(){
    abstract fun HomeMovie(): HomeMovieDao
    abstract fun FavoriteMovie(): FavoriteMovieDao
}