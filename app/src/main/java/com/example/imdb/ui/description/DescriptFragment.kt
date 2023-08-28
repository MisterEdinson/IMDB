package com.example.imdb.ui.description

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.imdb.R
import com.example.imdb.databinding.FragmentDescriptBinding
import com.example.imdb.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptFragment : Fragment() {

    private lateinit var binding: FragmentDescriptBinding
    val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.getString("id")
        id?.let { viewModel.getItemDesc(it) }
        viewModel.movieItemDescLiveData.observe(viewLifecycleOwner){
            binding.apply {
                imgCenterBg.load(it.imgBackground)
                imgCenterPoster.load(it.poster)
            }
        }

        binding.apply {
            tvBtnFact.setOnClickListener {
                findNavController().navigate(R.id.action_descriptFragment_to_descFactFragment2)
            }
            tvBtnTrailer.setOnClickListener {
                findNavController().navigate(R.id.action_descriptFragment_to_descTrailerFragment2)
            }
            tvBtnSimilar.setOnClickListener {
                findNavController().navigate(R.id.action_descriptFragment_to_descSimilarFragment2)
            }
        }
    }
}