package com.example.imdb.ui.description.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.imdb.R
import com.example.imdb.data.network.model.kinopoiskMovie.PersonsItem
import com.example.imdb.databinding.ItemActorBinding

class AdapterDescriptActror: RecyclerView.Adapter<AdapterDescriptActror.ActorHolder>() {
    class ActorHolder(view: View) : RecyclerView.ViewHolder(view)

    val callback = object : DiffUtil.ItemCallback<PersonsItem>(){
        override fun areItemsTheSame(oldItem: PersonsItem, newItem: PersonsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PersonsItem, newItem: PersonsItem): Boolean {
            return oldItem.id == newItem.id
        }

    }
    val list = AsyncListDiffer(this, callback)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ActorHolder(view)
    }

    override fun getItemCount(): Int {
        return list.currentList.size
    }

    override fun onBindViewHolder(holder: ActorHolder, position: Int) {
        val item = list.currentList[position]
        val binding = ItemActorBinding.bind(holder.itemView)
        binding.tvNameActor.text = item.name
        binding.imgPosterActor.load(item.photo)
        binding.tvPersonajeActor.text = item.description ?: item.profession
    }
}