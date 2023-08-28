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
    val movieAllFavoriteLiveData: MutableLiveData<List<HomeMovieModel>> = MutableLiveData()
    var sort: String? = null
    var search: String? = null

    init {
        getMovies()
        getAllFavorite()
    }
    //По умолчанию - первый запуск
    fun getMovies() {
        viewModelScope.launch {
            val response = repo.getMovies()
            moviesDefaultLiveData.value = response
        }
    }

    //поиск на сервере
    fun searchMovie(search: String, sort: String?) {
        viewModelScope.launch {
            val response = repo.searchMovie(search, sort)
            moviesDefaultLiveData.value = response
        }
    }
    // вернуть все избранные
    fun getAllFavorite() {
        viewModelScope.launch {
            val response = repo.getAllFavorite()
            movieAllFavoriteLiveData.value = response
        }
    }

    fun getAllLocal(){
        viewModelScope.launch {
            val res = repo.getLocal()
            moviesDefaultLiveData.value = res
        }
    }

    // добавить избранное
    fun addFavorite(favorite: HomeMovieModel){
       viewModelScope.launch {
           repo.addFavorite(favorite)
       }
    }

    // Удалить избранное
    fun deleteFavorite(favorite: HomeMovieModel){
        viewModelScope.launch {
            repo.delFavorite(favorite)
        }
    }
}