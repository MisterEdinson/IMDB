package com.example.imdb.data.network

import com.example.imdb.data.network.model.kinopoisk.Kinopoisk
import com.example.imdb.data.network.model.kinopoiskMovie.KinopoiskMovie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SimpleRetro {
    @GET("movie")
    suspend fun getMovies(): Kinopoisk

    @GET("movie")
    suspend fun getMoviesSearch(
        @Query("limit") limit: Int,
        @Query("search") search:String,
        @Query("field") field:String,
        @Query("sortField") sort:String,
        @Query("type") type:String,
    ): Kinopoisk

    @GET("movie/{movie_id}")
    suspend fun getItemMovies(@Path("movie_id") id: String): KinopoiskMovie
}