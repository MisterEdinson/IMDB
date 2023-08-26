package com.example.imdb.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_movie")
data class FavoriteMovieModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var idkp: String? = null,
    var idimdb: String? = null,
    var idtmdb: String? = null,
    var type: String? = null,
    var title: String? = null,
    var descShort: String? = null,
    var descLong: String? = null,
    var feesWorld: String? = null,
    var feesWorldCur: String? = null,
    var feesRus: String? = null,
    var feesRusCur: String? = null,
    var ratingKp: String? = null,
    var ratingImdb: String? = null,
    var votesKp: String? = null,
    var votesImdb: String? = null,
    var imgBackground: String? = null,
    var lenght: String? = null,
    var premierWorld: String? = null,
    var premierRus: String? = null,
    var year: String? = null,
    var poster: String? = null,
    var genres: String? = null,
    var facts: String? = null,
    var budget: String? = null,
    var budgetCur: String? = null,
    var countries: String? = null,
    var videos: String? = null,
    var person: String? = null,
    var namesynonym: String? = null,
    var similar: String? = null,
    var ageRating: String? = null,
)
