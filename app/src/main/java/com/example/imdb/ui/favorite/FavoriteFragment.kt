package com.example.imdb.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imdb.R
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.databinding.FragmentFavoriteBinding
import com.example.imdb.ui.home.AdapterMovies
import com.example.imdb.ui.home.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    val viewModel: MainViewModel by activityViewModels()
    private var adapter: AdapterMovies? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainContainer.apply {
            tvBtnFavorite.visibility = View.GONE
            tvBtnBack.visibility = View.VISIBLE
            spSort.visibility = View.GONE
            imgBtnClear.visibility = View.VISIBLE

            initAdapter()
            viewModel.getAllFavorite()
            viewModel.movieAllFavoriteLiveData.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    viewModel.getMovies()
                    findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
                }
                adapter?.list?.submitList(it)
            }

            tvBtnBack.setOnClickListener {
                viewModel.getMovies()
                findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
            }
        }
    }

    private fun initAdapter() {
        adapter = AdapterMovies(
            { add -> addFavorite(add) },
            { del -> delFavorite(del) },
            { nav -> navigationDesc(nav) })
        binding.mainContainer.rvMovies.adapter = adapter
        binding.mainContainer.rvMovies.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun addFavorite(add: HomeMovieModel) {
        viewModel.addFavorite(add)
        viewModel.getAllFavorite()
        Toast.makeText(context, "Добавлено: ${add.title}", Toast.LENGTH_SHORT).show()
    }

    private fun delFavorite(del: HomeMovieModel) {
        viewModel.deleteFavorite(del)
        viewModel.getAllFavorite()
        Toast.makeText(context, "Удалено: ${del.title}", Toast.LENGTH_SHORT).show()
    }

    private fun navigationDesc(nav: HomeMovieModel) {
        findNavController().navigate(R.id.action_homeFragment_to_descriptFragment)
    }
}