package com.example.imdb.domain.usecase

import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.data.network.model.kinopoisk.DocsItem
import com.example.imdb.data.network.model.kinopoisk.Kinopoisk

class MappingKinopoiskToHome {
    fun converter(kinopoisk: Kinopoisk) : List<HomeMovieModel>{
        val movies: List<DocsItem?>? = kinopoisk.docs
        val mapper = ModelKinopoiskToHome()
        val mapping: List<HomeMovieModel> =
            movies?.mapNotNull { item ->
                item.let {
                    mapper.converted(it)
                }
            } ?: emptyList()
        return mapping
    }
}