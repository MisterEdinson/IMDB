package com.example.imdb.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.imdb.R

class AdapterSort(private val context: Context?, private val arrSort: Array<String>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrSort.size
    }

    override fun getItem(p0: Int): Any {
        return p0
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.item_sort, p2, false)
        val element = view.findViewById<TextView>(R.id.tvSortItem)
        element.text = arrSort[p0]
        return view
    }
}