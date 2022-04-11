package com.backbase.ui.search.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.domain.model.CityDomainModel

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var onItemClick: (CityDomainModel) -> Unit = {}

    var items = mutableListOf<CityDomainModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MapItemViewHolder(MapItemViewHolder.binding(parent), onItemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MapItemViewHolder).bind(items[position])
    }
}
