package com.example.imdb.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.MainActivity
import com.example.imdb.R
import com.example.imdb.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class SplashFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getMovies()
        viewModel.getCountFavorite()
        loadData()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun loadData(){

        GlobalScope.launch(Dispatchers.Main) {
            delay(1000)
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        }
    }
}