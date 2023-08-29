package com.example.imdb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdb.R
import com.example.imdb.data.local.model.HomeMovieModel
import com.example.imdb.databinding.ItemMovieBinding

class AdapterMovies(
    val addFavorite: (HomeMovieModel) -> Unit,
    val delFavorite: (HomeMovieModel) -> Unit,
    val navigate: (HomeMovieModel) -> Unit,
) : RecyclerView.Adapter<AdapterMovies.MoviesHolder>() {

    private lateinit var binding: ItemMovieBinding

    class MoviesHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<HomeMovieModel>() {
        override fun areItemsTheSame(oldItem: HomeMovieModel, newItem: HomeMovieModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HomeMovieModel, newItem: HomeMovieModel): Boolean {
            return oldItem.favorite == newItem.favorite
        }
    }
    val list = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val item = list.currentList[position]
        var indicator = 0

        val binding = ItemMovieBinding.bind(holder.itemView)
        binding.apply {
            tvReutingKp.text = item.raitingKp
            tvReutingImdb.text = item.raitingImdb
            item.poster?.let { imgPoster.load(item.poster) }

            when (item.favorite) {
                0 -> imgFavorite.load(R.drawable.ic_fav_no_click)
                1 -> imgFavorite.load(R.drawable.ic_fav_click)
            }

            imgPoster.setOnClickListener {
                navigate(item)
            }

            imgFavorite.setOnClickListener {
                when (indicator) {
                    0 -> {
                        addFavorite(item)
                        imgFavorite.load(R.drawable.ic_fav_click)
                        indicator = 1
                    }

                    1 -> {
                        delFavorite(item)
                        imgFavorite.load(R.drawable.ic_fav_no_click)
                        indicator = 0
                    }
                }
            }
        }
    }
}