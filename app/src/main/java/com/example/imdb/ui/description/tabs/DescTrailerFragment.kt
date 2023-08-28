package com.example.imdb.ui.description.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.FactsItem
import com.example.imdb.data.network.model.kinopoiskMovie.Videos
import com.example.imdb.databinding.FragmentDescTrailerBinding
import com.example.imdb.ui.home.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescTrailerFragment : Fragment() {

    private lateinit var binding: FragmentDescTrailerBinding
    val viewModel: MainViewModel by activityViewModels()
    private val type = object : TypeToken<Videos>() {}.type
    private var videos: Videos? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescTrailerBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webViewTrailer.settings.javaScriptEnabled = true
        binding.webViewTrailer.webViewClient = WebViewClient()
        val gson = Gson()
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner){
            binding.webViewTrailer.loadUrl(it.videos.toString())
        }
    }

}