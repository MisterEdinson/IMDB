package com.example.imdb.ui.description.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.FactsItem
import com.example.imdb.data.network.model.kinopoiskMovie.PersonsItem
import com.example.imdb.databinding.FragmentDescFactBinding
import com.example.imdb.ui.description.adapters.AdapterDescriptFacts
import com.example.imdb.ui.home.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescFactFragment : Fragment() {

    private lateinit var binding: FragmentDescFactBinding
    val viewModel: MainViewModel by activityViewModels()
    private var adapter: AdapterDescriptFacts? = null
    private val listType = object : TypeToken<List<FactsItem>>() {}.type
    private var factssList: List<FactsItem?>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescFactBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val gson = Gson()
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner) {
            factssList = gson.fromJson(it.facts, listType)
            adapter?.list?.submitList(factssList)
        }
        binding.tvBtnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initAdapter() {
        adapter = AdapterDescriptFacts()
        binding.rvFacts.adapter = adapter
    }
}