package com.backbase.ui.search.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.backbase.R
import com.backbase.databinding.MapItemBinding
import com.backbase.domain.model.CityDomainModel

class MapItemViewHolder(
    private val binding: MapItemBinding,
    private val onItemClick: (CityDomainModel) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun binding(parent: ViewGroup) = MapItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        const val LAYOUT_ID = R.layout.map_item
    }

    @SuppressLint("SetTextI18n")
    fun bind(model: CityDomainModel) {
        binding.root.setOnClickListener { onItemClick.invoke(model) }
        binding.tvMapItemTitle.text = "${model.name} ${model.country}"
        binding.tvMapItemSubtitle.text = "${model.latitude} ${model.longitude}"
    }
}
