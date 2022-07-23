package com.example.contextualcards.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.ItemContextualCardLayoutBinding

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ContextualCardsAdapter : RecyclerView.Adapter<ContextualCardsAdapter.ContextualViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContextualViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ContextualViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class ContextualViewHolder(val binding : ItemContextualCardLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}