package com.example.contextualcards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.ItemCardLayoutBinding

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder =
        CardsViewHolder(
            ItemCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val childLayoutManager =
            LinearLayoutManager(
                holder.binding.contextualCardsRecycler.context,
                RecyclerView.HORIZONTAL,
                false
            )

        holder.binding.contextualCardsRecycler.apply {
            layoutManager = childLayoutManager
            setHasFixedSize(true)
            adapter = ContextualCardsAdapter()
//            setRecycledViewPool(viewPool)
        }
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class CardsViewHolder(val binding: ItemCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}