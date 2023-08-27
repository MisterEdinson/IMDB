package com.example.imdb.ui.description.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.PersonsItem
import com.example.imdb.databinding.FragmentDescMainBinding
import com.example.imdb.ui.description.adapters.AdapterDescriptActror
import com.example.imdb.ui.home.MainViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescMainFragment : Fragment() {

    private lateinit var binding: FragmentDescMainBinding
    val viewModel: MainViewModel by activityViewModels()
    private var adapter: AdapterDescriptActror? = null
    private val listType = object : TypeToken<List<PersonsItem>>() {}.type
    private var personsList: List<PersonsItem?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        val gson = Gson()
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner){
            binding.apply {
                tvDescCountry.text = it.countries
                tvDescYear.text = it.year
                tvDescTitle.text = it.title
                tvDescText.text = it.descLong
                tvDescKpRaiting.text = it.ratingKp
                tvDescImdbRaiting.text = it.ratingImdb

                tvDescDataRelize.text = it.premierWorld

                tvDescLanght.text = it.lenght
                tvDescBudget.text = it.budget

                tvDescFeesRus.text = it.feesRus
                tvDescFeesWorld.text = it.feesWorld
            }
            personsList = gson.fromJson(it.person, listType)
            adapter?.list?.submitList(personsList)
        }
    }

    private fun initAdapter() {
        adapter = AdapterDescriptActror()
        binding.rvActor.adapter = adapter
    }
}