package com.example.imdb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var type: String? = null,
    var title: String? = null,
    var desc: String? = null,
)
