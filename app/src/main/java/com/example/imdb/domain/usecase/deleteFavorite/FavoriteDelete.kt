package com.example.imdb.domain.usecase.deleteFavorite

import com.example.imdb.data.local.model.HomeMovieModel

class FavoriteDelete {
    fun deleteFavorite(del: HomeMovieModel): HomeMovieModel {
        return HomeMovieModel(
            id = del.id,
            idkp = del.idkp,
            idimdb = del.idimdb,
            idtmdb = del.idtmdb,
            type = del.type,
            title = del.title,
            desc = del.desc,
            year = del.year,
            poster = del.poster,
            countries = del.countries,
            genres = del.genres,
            lenght = del.lenght,
            raitingKp = del.raitingKp,
            raitingImdb = del.raitingImdb,
            votesKp = del.votesKp,
            votesImdb = del.votesImdb,
            favorite = 0
        )
    }
}