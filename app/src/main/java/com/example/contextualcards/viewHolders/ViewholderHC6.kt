package com.example.contextualcards.viewHolders

import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc1LayoutBinding
import com.example.contextualcards.databinding.ItemHc6LayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC6(private val binding: ItemHc6LayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroupsList: ArrayList<CardGroup>, position: Int, groupPosition: Int) {
        val cards = cardGroupsList[groupPosition].cards

        if (!cards[position].bg_color.isNullOrEmpty())
            binding.card.setCardBackgroundColor(Color.parseColor(cards[position].bg_color))

        if (!cards[position].icon?.image_url.isNullOrEmpty())
            Glide.with(binding.iconImage)
                .load(cardGroupsList[groupPosition].cards[position].icon?.image_url)
                .into(binding.iconImage)


        binding.title.text = cards[position].title

        if (!cards[position].formatted_title?.align.isNullOrEmpty())
            when (cards[position].formatted_title?.align) {
                "left" -> binding.title.textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                "right" -> binding.title.textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                "center" -> binding.title.textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
    }
}