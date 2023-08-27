package com.example.imdb.domain.usecase.mapperHome

import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.data.network.model.kinopoisk.DocsItem

class ModelKinopoiskToHome {
    fun converted(itemMovie: DocsItem?): HomeMovieModel {
        return HomeMovieModel(
            id = 0,
            idkp = itemMovie?.id.toString(),
            idimdb = itemMovie?.externalId?.imdb,
            idtmdb = itemMovie?.externalId?.tmdb.toString(),
            type = itemMovie?.type,
            title = itemMovie?.name,
            desc = itemMovie?.description,
            year = itemMovie?.year.toString(),
            poster = itemMovie?.poster?.previewUrl,
            countries = itemMovie?.countries.toString(),
            genres = itemMovie?.genres.toString(),
            lenght = itemMovie?.movieLength.toString(),
            raitingKp = itemMovie?.rating?.kp.toString(),
            raitingImdb = itemMovie?.rating?.imdb.toString(),
            votesKp = itemMovie?.votes?.kp.toString(),
            votesImdb = itemMovie?.votes?.imdb.toString(),
            favorite = 0
        )
    }
}