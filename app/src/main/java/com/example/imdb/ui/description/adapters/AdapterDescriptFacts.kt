package com.example.imdb.ui.description.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.FactsItem
import com.example.imdb.databinding.ItemFactBinding
import com.example.imdb.domain.util.removeHtmlTags

class AdapterDescriptFacts : RecyclerView.Adapter<AdapterDescriptFacts.FactHolder>() {
    class FactHolder(view: View): RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<FactsItem>(){
        override fun areItemsTheSame(oldItem: FactsItem, newItem: FactsItem): Boolean {
            return oldItem.value == newItem.value
        }

        override fun areContentsTheSame(oldItem: FactsItem, newItem: FactsItem): Boolean {
            return oldItem.value == newItem.value
        }
    }

    val list = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_fact, parent, false)
        return FactHolder(view)
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }

    override fun onBindViewHolder(holder: FactHolder, position: Int) {
        val item = list.currentList[position]
        val binding = ItemFactBinding.bind(holder.itemView)
        binding.tvCountFacts.text = (position+1).toString()
        binding.tvTextFact.text = removeHtmlTags(item.value)
    }
}