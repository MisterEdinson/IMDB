package com.example.imdb.domain

import com.example.imdb.data.network.SimpleRetro
import com.example.imdb.data.network.model.kinopoisk.Kinopoisk
import javax.inject.Inject

class Repository @Inject constructor(private val retro: SimpleRetro) {
    suspend fun getMovies(): Kinopoisk{
        return retro.getMovies()
    }
}