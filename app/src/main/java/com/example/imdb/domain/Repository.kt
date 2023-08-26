package com.example.imdb.domain

import com.example.imdb.data.local.dao.HomeMovieDao
import com.example.imdb.data.local.model.FavoriteMovieModel
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.data.network.SimpleRetro
import com.example.imdb.domain.usecase.mapperFavorite.MappingKinopoiskToFavorite
import com.example.imdb.domain.usecase.mapperHome.MappingKinopoiskToHome
import javax.inject.Inject

class Repository @Inject constructor(
    private val retro: SimpleRetro,
    private val homeMovies: HomeMovieDao
) {
    suspend fun getMovies(): List<HomeMovieModel> {
        return MappingKinopoiskToHome().converter(retro.getMovies())
    }
    suspend fun searchMovie(search: String, sort: String?): List<HomeMovieModel> {
        return MappingKinopoiskToHome()
            .converter(
                retro.getMoviesSearch(
                    6,
                    search,
                    "name",
                    sort ?: "rating.kp"
                )
            )
    }
    suspend fun getMovie(id:String): FavoriteMovieModel{
        return MappingKinopoiskToFavorite().converter(retro.getItemMovies(id))
    }

    suspend fun addFavorite(insert:HomeMovieModel){
        homeMovies.insertHomeMovie(insert)
    }

    suspend fun getAllFavorite(): List<HomeMovieModel>{
        return homeMovies.getAllFavorite()
    }
}