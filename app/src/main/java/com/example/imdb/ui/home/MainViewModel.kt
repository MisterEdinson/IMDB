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
    val moviesLiveData: MutableLiveData<List<HomeMovieModel>> = MutableLiveData()
    val movieLiveData: MutableLiveData<FavoriteMovieModel> = MutableLiveData()
    val movieFavorite: MutableLiveData<List<HomeMovieModel>> = MutableLiveData()
    var sort: String? = null
    var search: String? = null

    init {
        getMovies()
    }

    fun getMovies() {
        viewModelScope.launch {
            val response = repo.getMovies()
            moviesLiveData.value = response
        }
    }

    fun searchMovie(search: String, sort: String?) {
        viewModelScope.launch {
            val response = repo.searchMovie(search, sort)
            moviesLiveData.value = response
        }
    }

    fun getAllFavorite() {
        viewModelScope.launch {
            val response = repo.getAllFavorite()
            movieFavorite.value = response
        }
    }

    fun getMovie(id: String) {
        viewModelScope.launch {
            val response = repo.getMovie(id)
            movieLiveData.value = response
        }
    }

    fun insert(favorite: HomeMovieModel) {
        viewModelScope.launch {
            repo.addFavorite(favorite)
        }
    }
}