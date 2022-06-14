package com.albertomier.klikinwaylettest.ui.adapter

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.klikinwaylettest.R
import com.albertomier.klikinwaylettest.databinding.CategoryItemBinding

class CategoryViewHolder(private val binding: CategoryItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(text: String, onClickListener: (String) -> Unit) {
        val context: Context = binding.image.context

        binding.category = text

        when (text) {
            "FOOD" -> {
                binding.name.setTextColor(context.getColor(R.color.yellow))
                binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.catering_colour
                    )
                )
            }
            "BEAUTY" -> {
                binding.name.setTextColor(context.getColor(R.color.green))
                binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.car_wash_colour
                    )
                )
            }
            "LEISURE" -> {
                binding.name.setTextColor(context.getColor(R.color.purple))
                binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.leisure_colour
                    )
                )
            }
            "SHOPPING" -> {
                binding.name.setTextColor(context.getColor(R.color.pink))
                binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.cart_colour
                    )
                )
            }
            "OTHER" -> {
                binding.name.setTextColor(context.getColor(R.color.orange))
                binding.image.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.mipmap.ees_colour
                    )
                )
            }
        }

        itemView.setOnClickListener {
            onClickListener(text)
        }
    }
}