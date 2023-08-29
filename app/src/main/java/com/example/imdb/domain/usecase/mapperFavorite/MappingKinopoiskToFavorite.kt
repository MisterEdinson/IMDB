package com.example.imdb.domain.usecase.mapperFavorite

import com.example.imdb.data.local.model.FavoriteMovieModel
import com.example.imdb.data.network.model.kinopoiskMovie.KinopoiskMovie
import com.google.gson.Gson
import okhttp3.internal.wait

class MappingKinopoiskToFavorite {
    fun converter(kinopoisk: KinopoiskMovie): FavoriteMovieModel {
        return FavoriteMovieModel(
            id = 0,
            idkp = kinopoisk.id.toString(),
            idimdb = kinopoisk.externalId?.imdb,
            idtmdb = kinopoisk.externalId?.tmdb.toString(),
            type = kinopoisk.type,
            title = kinopoisk.name,
            descShort = kinopoisk.shortDescription,
            descLong = kinopoisk.description,
            feesWorld = kinopoisk.fees?.world?.value.toString(),
            feesWorldCur = kinopoisk.fees?.world?.currency,
            feesRus = kinopoisk.fees?.russia?.value.toString(),
            feesRusCur = kinopoisk.fees?.russia?.currency,
            ratingKp = kinopoisk.rating?.kp.toString(),
            ratingImdb = kinopoisk.rating?.imdb.toString(),
            votesKp = kinopoisk.votes?.kp.toString(),
            votesImdb = kinopoisk.votes?.imdb.toString(),
            imgBackground = kinopoisk.backdrop?.url,
            lenght = kinopoisk.movieLength.toString(),
            premierWorld = kinopoisk.premiere?.world,
            premierRus = kinopoisk.premiere?.russia,
            year = kinopoisk.year.toString(),
            poster = kinopoisk.poster?.url,
            genres = Gson().toJson(kinopoisk.genres),
            facts = Gson().toJson(kinopoisk.facts),
            budget = kinopoisk.budget?.value.toString(),
            budgetCur = kinopoisk.budget?.currency,
            countries = kinopoisk.countries?.get(0)?.name,
            videos = kinopoisk.videos?.trailers?.get(0)?.url,
            person = Gson().toJson(kinopoisk.persons),
            namesynonym = Gson().toJson(kinopoisk.names),
            similar = Gson().toJson(kinopoisk.similarMovies),
            ageRating = kinopoisk.ageRating.toString(),
        )
    }
}