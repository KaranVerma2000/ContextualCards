package com.example.contextualcards.viewHolders

import android.content.Intent
import android.graphics.Color
import android.net.Uri
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

        if (!cardGroupsList[groupPosition].cards[position].url.isNullOrEmpty()) {
            binding.card.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(cardGroupsList[groupPosition].cards[position].url)
                binding.root.context.startActivity(intent)
            }
        }
    }
}