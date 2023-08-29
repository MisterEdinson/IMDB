package com.example.imdb.ui.description.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.imdb.databinding.FragmentDescTrailerBinding
import com.example.imdb.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescTrailerFragment : Fragment() {

    private lateinit var binding: FragmentDescTrailerBinding
    val viewModel: MainViewModel by activityViewModels()

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

        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner) {
            binding.webViewTrailer.loadUrl(it.videos.toString())
        }
    }

}