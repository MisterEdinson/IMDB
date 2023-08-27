package com.example.imdb.data.local.model

import androidx.room.DeleteTable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "home_movie")
data class HomeMovieModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var type: String? = null,
    var title: String? = null,
    var desc: String? = null,
    var year: String? = null,
    var poster: String? = null,
    var countries: String? = null,
    var genres: String? = null,
    var lenght: String? = null,
    var raitingKp: String? = null,
    var raitingImdb: String? = null,
    var votesKp: String? = null,
    var votesImdb: String? = null,
)