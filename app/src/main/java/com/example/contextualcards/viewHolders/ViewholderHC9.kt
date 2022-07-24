package com.example.contextualcards.viewHolders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc1LayoutBinding
import com.example.contextualcards.databinding.ItemHc9LayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC9(val binding: ItemHc9LayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroupsList: ArrayList<CardGroup>, position: Int, groupPosition: Int) {

        Glide.with(binding.root)
            .load(cardGroupsList[groupPosition].cards[position].bg_image?.image_url)
            .into(binding.image)
    }
}