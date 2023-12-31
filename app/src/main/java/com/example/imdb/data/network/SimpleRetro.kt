package com.example.imdb.data.network

import com.example.imdb.data.network.model.kinopoisk.Kinopoisk
import retrofit2.http.GET

interface SimpleRetro {
    @GET("competitions")
    suspend fun getMovies(): Kinopoisk
}