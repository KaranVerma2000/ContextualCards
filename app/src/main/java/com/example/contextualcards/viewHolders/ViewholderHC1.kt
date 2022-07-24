package com.example.contextualcards.viewHolders

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc1LayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC1(val binding: ItemHc1LayoutBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(cardGroupsList: ArrayList<CardGroup>, position: Int, groupPosition: Int) {

        if (!cardGroupsList[groupPosition].cards[position].bg_color.isNullOrEmpty())
            binding.card.setCardBackgroundColor(Color.parseColor(cardGroupsList[groupPosition].cards[position].bg_color))

        binding.title.text = cardGroupsList[groupPosition].cards[position].formatted_title?.text
        Glide.with(binding.iconImage)
            .load(cardGroupsList[groupPosition].cards[position].icon?.image_url)
            .into(binding.iconImage)
    }
}