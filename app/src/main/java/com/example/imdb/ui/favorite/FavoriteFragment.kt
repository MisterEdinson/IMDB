package com.example.imdb.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.imdb.R
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.databinding.DialogClearBinding
import com.example.imdb.databinding.FragmentFavoriteBinding
import com.example.imdb.ui.home.AdapterMovies
import com.example.imdb.ui.home.MainViewModel
import com.example.imdb.ui.home.SearchTextWatcher
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
                adapter?.list?.submitList(it)
            }

            tvBtnBack.setOnClickListener {
                viewModel.getMovies()
                findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
            }

            imgBtnClear.setOnClickListener{
                dialogClear()
            }

            etSearch.addTextChangedListener(object : SearchTextWatcher {
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    searchFavLocal(p0.toString())
                }
            })
        }
    }

    private fun initAdapter() {
        adapter = AdapterMovies(
            { add -> delFavorite(add) },
            { del -> delFavorite(del) },
            { nav -> navigationDesc(nav) })
        binding.mainContainer.rvMovies.adapter = adapter
        binding.mainContainer.rvMovies.layoutManager = GridLayoutManager(activity, 2)
    }

    private fun delFavorite(del: HomeMovieModel) {
        viewModel.deleteFavorite(del)
        viewModel.getAllFavorite()
        Toast.makeText(context, "Удалено: ${del.title}", Toast.LENGTH_SHORT).show()
    }

    private fun navigationDesc(nav: HomeMovieModel) {
        val bundle = Bundle()
        nav.idkp?.let { bundle.putString("id", it) }
        findNavController().navigate(R.id.action_favoriteFragment_to_descriptFragment, bundle)
    }

    private fun searchFavLocal(search:String){
        if(search.length > 0){
            viewModel.searchFavoriteLocal(search)
        }else{
            viewModel.getAllFavorite()
        }
    }

    private fun dialogClear() {
        val builder = AlertDialog.Builder(binding.root.context)
        val dialogBind =
            DialogClearBinding.inflate(LayoutInflater.from(binding.root.context), null, false)
        builder.setView(dialogBind.root)
        val dialog = builder.create()

        dialogBind.tvNoClear.setOnClickListener {
            dialog.dismiss()
        }
        dialogBind.tvYesClear.setOnClickListener {
            dialog.dismiss()
            viewModel.deleteAllFavorite()
            viewModel.favoriteIndicator.value = 0
            findNavController().navigate(R.id.action_favoriteFragment_to_homeFragment)
        }
        dialog.show()
    }
}