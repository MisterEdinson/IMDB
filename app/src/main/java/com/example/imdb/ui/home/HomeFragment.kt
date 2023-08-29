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
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
        viewModel.getCountFavorite()
        viewModel.moviesDefaultLiveData.observe(viewLifecycleOwner) {
            adapter?.list?.submitList(it)
        }
        binding.mainContainer.apply {
            etSearch.addTextChangedListener(object : SearchTextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    p0?.let {
                        if (p0.length > 2) {
                            viewModel.search = p0.toString()
                            searchMovie()
                        }
                    }
                }
            })

            spSort.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    viewModel.sort =
                        SortConvert().converted(resources.getStringArray(R.array.sort_array)[p2])
                    searchMovie()
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {}
            }
            visibleBtnFavorite()
            tvBtnFavorite.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_favoriteFragment)
            }
        }
    }

    private fun visibleBtnFavorite() {
        viewModel.favoriteIndicator.observe(viewLifecycleOwner) {
            if (it > 0) {
                binding.mainContainer.tvBtnFavorite.visibility = View.VISIBLE
            } else {
                binding.mainContainer.tvBtnFavorite.visibility = View.INVISIBLE
            }
        }
    }

    private fun initAdapter() {
        adapter = AdapterMovies(
            { add -> addFavorite(add) },
            { del -> delFavorite(del) },
            { nav -> navigationDesc(nav) }
        )
        binding.mainContainer.rvMovies.adapter = adapter
        binding.mainContainer.rvMovies.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        adapterSort = AdapterSort(context, resources.getStringArray(R.array.sort_array))
        binding.mainContainer.spSort.adapter = adapterSort
    }

    private fun searchMovie() {
        job?.cancel()
        job = MainScope().launch {
            viewModel.search?.let {
                if (it.length > 2) {
                    viewModel.searchMovie(it, viewModel.sort)
                }
            }
        }
    }

    private fun addFavorite(add: HomeMovieModel) {
        viewModel.addFavorite(add)
        //viewModel.getAllLocal()
        binding.mainContainer.tvBtnFavorite.visibility = View.VISIBLE
        Toast.makeText(context, "Добавлено: ${add.title}", Toast.LENGTH_SHORT).show()
    }

    private fun delFavorite(del: HomeMovieModel) {
        viewModel.deleteFavorite(del)
        //viewModel.getAllLocal()
        Toast.makeText(context, "Удалено: ${del.title}", Toast.LENGTH_SHORT).show()
    }

    private fun navigationDesc(nav: HomeMovieModel) {

        if(nav.idkp == "null"){
            Toast.makeText(context, "Ошибка кинопоиска", Toast.LENGTH_LONG).show()
        }else{
            val bundle = Bundle()
            nav.idkp?.let { bundle.putString("id", nav.idkp) }
            findNavController().navigate(R.id.action_homeFragment_to_descriptFragment, bundle)
        }
    }
}