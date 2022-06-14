package com.albertomier.klikinwaylettest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.klikinwaylettest.databinding.ShopItemBinding
import com.albertomier.klikinwaylettest.domain.model.Shop

class ShopAdapter(
    private var shopList: List<Shop>,
    private val onClickListener: (Shop) -> Unit
) : RecyclerView.Adapter<ShopViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopViewHolder {
        val binding = ShopItemBinding.inflate(LayoutInflater.from(parent.context))
        return ShopViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ShopViewHolder, position: Int) {
        val dog = shopList[position]
        holder.bind(dog, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!shopList.isNullOrEmpty()) {
            return shopList.size
        }
        return 0
    }
}