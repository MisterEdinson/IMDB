package com.example.imdb.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.imdb.R
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
            adapter?.list?.submitList(it.docs)
        }

        binding.etSearch.addTextChangedListener( object : SearchTextWatcher{
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                p0?.let {
                    if (p0.length > 2){
                        searchMovie(p0.toString())
                    }
                }
            }
        })
    }
    private fun initAdapter(){
        adapter = AdapterMovies()
        binding.rvMovies.adapter = adapter

        adapterSort = AdapterSort(context, resources.getStringArray(R.array.sort_array))
        binding.spSort.adapter = adapterSort
    }

    private fun searchMovie(search:String){
        job?.cancel()
        job = MainScope().launch {
            viewModel.searchMovie(search)
        }
    }
}