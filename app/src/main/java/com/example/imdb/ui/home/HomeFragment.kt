package com.example.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    val viewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentHomeBinding
    private var adapter: AdapterMovies? = null
    private var adapterSort: AdapterSort? = null
    private var job: Job? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdapter()
        viewModel.moviesLiveData.observe(viewLifecycleOwner){
            adapter?.list?.submitList(it)
        }
        binding.apply {
            mainContainer.etSearch.addTextChangedListener( object : SearchTextWatcher{
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        if (p0.length > 2){
                            viewModel.search = p0.toString()
                            searchMovie()
                        }
                    }
                }
            })

            mainContainer.spSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.sort = SortConvert().converted(resources.getStringArray(R.array.sort_array)[p2])
                    searchMovie()
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }

            mainContainer.tvBtnFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }
    }
    private fun initAdapter(){
        adapter = AdapterMovies({ favorite -> addFavorite(favorite)})
        binding.mainContainer.rvMovies.adapter = adapter

        adapterSort = AdapterSort(context, resources.getStringArray(R.array.sort_array))
        binding.mainContainer.spSort.adapter = adapterSort
    }

    private fun searchMovie(){
        job?.cancel()
        job = MainScope().launch {
            viewModel.search?.let {
                if(it.length > 2){
                    viewModel.searchMovie(it, viewModel.sort)
                }
            }
        }
    }

    private fun addFavorite(favorite: HomeMovieModel){
        viewModel.insert(favorite)
    }

    private fun navigationDesc(){
        findNavController().navigate(R.id.action_homeFragment_to_descriptFragment)
    }
}