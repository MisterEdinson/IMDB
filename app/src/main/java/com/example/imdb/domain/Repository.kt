package com.example.imdb.domain

import com.example.imdb.data.local.dao.FavoriteMovieDao
import com.example.imdb.data.local.dao.HomeMovieDao
import com.example.imdb.data.local.model.FavoriteMovieModel
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.data.network.SimpleRetro
import com.example.imdb.domain.usecase.addFavorite.FavoriteAdd
import com.example.imdb.domain.usecase.deleteFavorite.FavoriteDelete
import com.example.imdb.domain.usecase.mapperFavorite.MappingKinopoiskToFavorite
import com.example.imdb.domain.usecase.mapperHome.MappingKinopoiskToHome
import javax.inject.Inject

class Repository @Inject constructor(
    private val retro: SimpleRetro,
    private val homeMovies: HomeMovieDao,
    private val itemDesc: FavoriteMovieDao
) {
    suspend fun getMovies(): List<HomeMovieModel> {
        val default = MappingKinopoiskToHome().converter(retro.getMovies())
        homeMovies.deleteOld()
        homeMovies.insertHomeMovie(default)
        return homeMovies.getAll()
    }

    suspend fun getLocal(): List<HomeMovieModel>{
        return homeMovies.getAll()
    }

    suspend fun searchMovie(search: String, sort: String?): List<HomeMovieModel> {
        homeMovies.deleteOld()
        val res = MappingKinopoiskToHome()
            .converter(
                retro.getMoviesSearch(
                    6,
                    search,
                    "name",
                    sort ?: "rating.kp"
                )
            )
        homeMovies.insertHomeMovie(res)
        return homeMovies.getAll()
    }

    suspend fun getAllFavorite(): List<HomeMovieModel> {
        return homeMovies.getAllFavorite(1)
    }

    suspend fun addFavorite(insert: HomeMovieModel) {
        homeMovies.updateMovie(FavoriteAdd().add(insert))
    }

    suspend fun delFavorite(delete: HomeMovieModel) {
        homeMovies.updateMovie(FavoriteDelete().deleteFavorite(delete))
    }

    suspend fun getItem(id:String): FavoriteMovieModel{
        var local = itemDesc.getItem(id)
        if(local == null){
            itemDesc.insertItem(MappingKinopoiskToFavorite().converter(retro.getItemMovies(id)))
            local = itemDesc.getItem(id)
        }
        return local
    }
}