package com.example.imdb.ui.description.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.SimilarMoviesItem
import com.example.imdb.databinding.ItemSimilarBinding

class AdapterDescriptSimilar : RecyclerView.Adapter<AdapterDescriptSimilar.SimilarHolder>() {
    class SimilarHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<SimilarMoviesItem>() {
        override fun areItemsTheSame(
            oldItem: SimilarMoviesItem,
            newItem: SimilarMoviesItem
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: SimilarMoviesItem,
            newItem: SimilarMoviesItem
        ): Boolean {
            return oldItem.name == newItem.name
        }
    }
    val list = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimilarHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_similar, parent, false)
        return SimilarHolder(view)
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }

    override fun onBindViewHolder(holder: SimilarHolder, position: Int) {
        val item = list.currentList[position]
        val binding = ItemSimilarBinding.bind(holder.itemView)

        binding.apply {
            tvNameFilm.text = item.name
            tvNameAlternative.text = item.alternativeName
            imgPosterFilm.load(item.poster?.previewUrl)
        }
    }
}