package com.example.imdb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.data.local.model.FavoriteMovieModel
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val moviesDefaultLiveData: MutableLiveData<List<HomeMovieModel>> = MutableLiveData()
    val movieItemFavoriteLiveData: MutableLiveData<FavoriteMovieModel> = MutableLiveData()
    val movieAllFavoriteLiveData: MutableLiveData<List<HomeMovieModel>> = MutableLiveData()
    var sort: String? = null
    var search: String? = null

    init {
        getMovies()
        getAllFavorite()
    }

    fun getMovies() {
        viewModelScope.launch {
            val response = repo.getMovies()
            moviesDefaultLiveData.value = response
        }
    }

    fun searchMovie(search: String, sort: String?) {
        viewModelScope.launch {
            val response = repo.searchMovie(search, sort)
            moviesDefaultLiveData.value = response
        }
    }

    fun getAllFavorite() {
        viewModelScope.launch {
            val response = repo.getAllFavorite()
            movieAllFavoriteLiveData.value = response
        }
    }

    fun getMovie(id: String) {
        viewModelScope.launch {
            val response = repo.getMovie(id)
            movieItemFavoriteLiveData.value = response
        }
    }

    fun clickFavorite(favorite: HomeMovieModel) {
        viewModelScope.launch {
            if(repo.searchFavId(favorite.idkp.toString()).title != null){
                deleteFavorite(favorite)
            }else{
                repo.addFavorite(favorite)
            }
        }
    }
    fun addFavorite(favorite: HomeMovieModel){
       viewModelScope.launch {
           repo.addFavorite(favorite)
       }
    }
    fun deleteFavorite(favorite: HomeMovieModel){
        viewModelScope.launch {
            repo.delFavorite(favorite)
        }
    }
}