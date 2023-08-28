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

    suspend fun getLocal(): List<HomeMovieModel> {
        return homeMovies.getAll()
    }

    suspend fun searchMovie(search: String, sort: String?): List<HomeMovieModel> {

        val res = MappingKinopoiskToHome()
            .converter(
                retro.getMoviesSearch(
                    30,
                    search,
                    "name",
                    sort ?: "rating.kp",
                    "movie"
                )
            )
        homeMovies.deleteOld()
        homeMovies.insertHomeMovie(res)
        return homeMovies.getAll()
    }

    suspend fun searchFavoriteLocal(search: String): List<HomeMovieModel> {
        return homeMovies.searchLocalFavorite(search)
    }

    suspend fun getAllFavorite(): List<HomeMovieModel> {
        return homeMovies.getAllFavorite()
    }

    suspend fun addFavorite(insert: HomeMovieModel) {
        homeMovies.updateMovie(FavoriteAdd().add(insert))
    }

    suspend fun delFavorite(delete: HomeMovieModel) {
        homeMovies.updateMovie(FavoriteDelete().deleteFavorite(delete))
    }

    suspend fun delAllFavorite() {
        homeMovies.deleteAllFavorite()
    }

    suspend fun getCountFavorite(): Int {
        return homeMovies.getcountFavorite(1)
    }

    suspend fun getItem(id: String): FavoriteMovieModel {
        val checkLoc = itemDesc.checkLocal(id)
        return if (checkLoc == 0) {
            itemDesc.insertItem(MappingKinopoiskToFavorite().converter(retro.getItemMovies(id)))
            itemDesc.getItem(id)
        } else {
            itemDesc.getItem(id)
        }
    }
}