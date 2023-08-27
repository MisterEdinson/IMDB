package com.example.imdb.ui.description.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentDescTrailerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescTrailerFragment : Fragment() {

    private lateinit var binding: FragmentDescTrailerBinding
    val viewNavModel: DescriptionViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescTrailerBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewNavModel.navLiveData.observe(viewLifecycleOwner){

        }
    }

}