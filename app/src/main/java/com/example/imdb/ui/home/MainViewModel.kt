package com.example.imdb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imdb.data.network.model.kinopoisk.Kinopoisk
import com.example.imdb.domain.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: Repository) : ViewModel() {
    val moviesLiveData: MutableLiveData<Kinopoisk> = MutableLiveData()
    init {
        getMovies()
    }
    fun getMovies(){
        viewModelScope.launch {
            val response = repo.getMovies()
            moviesLiveData.value = response
        }
    }
}