package com.example.imdb.ui.description.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.PersonsItem
import com.example.imdb.data.network.model.kinopoiskMovie.SimilarMoviesItem
import com.example.imdb.databinding.FragmentDescSimilarBinding
import com.example.imdb.ui.description.adapters.AdapterDescriptSimilar
import com.example.imdb.ui.home.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescSimilarFragment : Fragment() {

    private lateinit var binding: FragmentDescSimilarBinding
    private var adapter: AdapterDescriptSimilar? = null
    val viewModel: MainViewModel by activityViewModels()
    private val listType = object : TypeToken<List<SimilarMoviesItem>>() {}.type
    private var similarList: List<SimilarMoviesItem?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescSimilarBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val gson = Gson()
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner){
            similarList = gson.fromJson(it.similar, listType)
            adapter?.list?.submitList(similarList)
        }
    }

    private fun initAdapter() {
        adapter = AdapterDescriptSimilar()
        binding.rvSimilar.adapter = adapter
    }

}