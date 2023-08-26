package com.example.imdb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoisk.DocsItem
import com.example.imdb.databinding.ItemMovieBinding
import com.example.imdb.domain.util.loadImage

class AdapterMovies : RecyclerView.Adapter<AdapterMovies.MoviesHolder>() {

    private lateinit var binding: ItemMovieBinding
    class MoviesHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<DocsItem>(){
        override fun areItemsTheSame(oldItem: DocsItem, newItem: DocsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DocsItem, newItem: DocsItem): Boolean {
            return oldItem.id == newItem.id
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
        val binding = ItemMovieBinding.bind(holder.itemView)
        binding.apply {
            tvReutingKp.text = item.rating?.kp.toString()
            tvReutingImdb.text = item.rating?.imdb.toString()
            item.poster?.previewUrl?.let { imgPoster.loadImage(it) }

            imgPoster.setOnClickListener{
                Toast.makeText(root.context, item.id.toString(), Toast.LENGTH_SHORT).show()
            }
            imgFavorite.setOnClickListener{
                Toast.makeText(root.context, item.name.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}