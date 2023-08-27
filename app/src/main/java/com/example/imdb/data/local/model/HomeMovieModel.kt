package com.example.imdb.data.local.model

import androidx.room.DeleteTable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "home_movie" , indices = [Index(value = ["idkp"], unique = true)])
data class HomeMovieModel(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var idkp: String? = null,
    var idtmdb: String? = null,
    var idimdb: String? = null,
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
    var favorite: Int? = null
)