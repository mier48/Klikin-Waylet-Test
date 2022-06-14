package com.albertomier.klikinwaylettest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertomier.klikinwaylettest.databinding.CategoryItemBinding

class CategoryAdapter(
    private var categoryList: List<String>,
    private val onClickListener: (String) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = CategoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val text = categoryList[position]
        holder.bind(text, onClickListener)
    }

    override fun getItemCount(): Int {
        if (!categoryList.isNullOrEmpty()) {
            return categoryList.size
        }
        return 0
    }
}