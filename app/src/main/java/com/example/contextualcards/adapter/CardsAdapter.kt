package com.example.contextualcards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.ItemCardLayoutBinding
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class CardsAdapter : RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var cardGroupsList : ArrayList<CardGroup> = arrayListOf()
    private val viewPool = RecyclerView.RecycledViewPool()

    fun setCardGroups(cardGroupsList : ArrayList<CardGroup>){
        this.cardGroupsList = cardGroupsList
        notifyDataSetChanged()
    }

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
            adapter = ContextualCardsAdapter(cardGroupsList, position)
        }
    }

    override fun getItemCount(): Int = cardGroupsList.size

    class CardsViewHolder(val binding: ItemCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}