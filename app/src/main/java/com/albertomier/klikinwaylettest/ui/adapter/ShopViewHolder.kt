package com.albertomier.klikinwaylettest.ui.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.databinding.ShopItemBinding
import com.albertomier.klikinwaylettest.domain.model.Shop

class ShopViewHolder(private val binding: ShopItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(shop: Shop, onClickListener: (Shop) -> Unit) {
        val context: Context = binding.shopImage.context
        binding.shop = shop

        if (!shop.photos.isNullOrEmpty()) {
            binding.shopImage.load(shop.photos[0].thumbnail.medium) {
                crossfade(true)
                placeholder(R.mipmap.placeholder)
                transformations(RoundedCornersTransformation(8f))
            }
        }

        when (shop.category) {
            "FOOD" -> {
                binding.infoLayout.setBackgroundColor(context.getColor(R.color.yellow))
                binding.typeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.catering_white
                    )
                )
            }
            "BEAUTY" -> {
                binding.infoLayout.setBackgroundColor(context.getColor(R.color.green))
                binding.typeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.car_wash_white
                    )
                )
            }
            "LEISURE" -> {
                binding.infoLayout.setBackgroundColor(context.getColor(R.color.purple))
                binding.typeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.leisure_white
                    )
                )
            }
            "SHOPPING" -> {
                binding.infoLayout.setBackgroundColor(context.getColor(R.color.pink))
                binding.typeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.cart_white
                    )
                )
            }
            "OTHER" -> {
                binding.infoLayout.setBackgroundColor(context.getColor(R.color.orange))
                binding.typeIcon.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.ees_white
                    )
                )
            }
        }

        if (shop.distance > 0.00) {
            binding.distance.text = String.format("%1$,.2f KM", shop.distance)
        } else {
            binding.distance.isVisible = false
        }

        itemView.setOnClickListener {
            onClickListener(shop)
        }
    }
}