package com.example.contextualcards.viewHolders

import android.content.Intent
import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.contextualcards.databinding.ItemHc1LayoutBinding
import com.example.contextualcards.databinding.ItemHc5LayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ViewholderHC5(val binding: ItemHc5LayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(cardGroupsList: ArrayList<CardGroup>, position: Int, groupPosition: Int) {

        if (!cardGroupsList[groupPosition].cards[position].bg_color.isNullOrEmpty())
            Glide.with(binding.root)
                .load(cardGroupsList[groupPosition].cards[position].bg_image?.image_url)
                .into(binding.image)

        if (!cardGroupsList[groupPosition].cards[position].url.isNullOrEmpty()) {
            binding.card.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(cardGroupsList[groupPosition].cards[position].url)
                binding.root.context.startActivity(intent)
            }
        }
    }
}