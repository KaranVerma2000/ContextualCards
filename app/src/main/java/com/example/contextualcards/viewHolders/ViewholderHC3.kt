package com.example.contextualcards.viewHolders

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc1LayoutBinding
import com.example.contextualcards.databinding.ItemHc3LayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC3(var binding: ItemHc3LayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroupsList: ArrayList<CardGroup>, position: Int, groupPosition: Int) {
        val cards = cardGroupsList[groupPosition].cards

        if (!cards[position].bg_color.isNullOrEmpty())
            binding.card.setCardBackgroundColor(Color.parseColor(cards[position].bg_color))

        if (!cards[position].bg_image?.image_url.isNullOrEmpty())
            Glide.with(binding.root).load(cards[position].bg_image?.image_url).into(binding.image)

        binding.title.text = cards[position].formatted_title?.text
        binding.desc.text = cards[position].formatted_description?.text

        binding.btn.text = cards[position].cta?.get(0)?.text
        binding.btn.setTextColor(Color.parseColor(cards[position].cta?.get(0)?.text_color))
        binding.btn.setBackgroundColor(Color.parseColor(cards[position].cta?.get(0)?.bg_color))
        binding.btn.setOnClickListener {

        }

    }
}