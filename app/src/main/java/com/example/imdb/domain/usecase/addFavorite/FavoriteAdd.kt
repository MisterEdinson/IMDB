package com.example.imdb.domain.usecase.addFavorite

import com.example.imdb.data.local.model.HomeMovieModel

class FavoriteAdd {
    fun add(insert: HomeMovieModel): HomeMovieModel {
        return HomeMovieModel(
            id = insert.id,
            idkp = insert.idkp,
            idimdb = insert.idimdb,
            idtmdb = insert.idtmdb,
            type = insert.type,
            title = insert.title,
            desc = insert.desc,
            year = insert.year,
            poster = insert.poster,
            countries = insert.countries,
            genres = insert.genres,
            lenght = insert.lenght,
            raitingKp = insert.raitingKp,
            raitingImdb = insert.raitingImdb,
            votesKp = insert.votesKp,
            votesImdb = insert.votesImdb,
            favorite = 1
        )
    }
}