package com.example.imdb.ui.description.tabs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.imdb.R
import com.example.imdb.databinding.FragmentDescMainBinding
import com.example.imdb.databinding.FragmentDescriptBinding
import com.example.imdb.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescMainFragment : Fragment() {

    private lateinit var binding: FragmentDescMainBinding
    val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner){
            binding.apply {
                tvDescType.text = it.type
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
        }
    }
}