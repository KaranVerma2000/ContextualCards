package com.example.contextualcards.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.ItemCardLayoutBinding
import com.example.contextualcards.model.CardGroup
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.lang.reflect.Type

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class CardsAdapter(private val sp: SharedPreferences) :
    RecyclerView.Adapter<CardsAdapter.CardsViewHolder>() {

    private var cardGroupsList: ArrayList<CardGroup> = arrayListOf()
    private var hc3CardPos: Int = -1

    private var dismissList: ArrayList<Int> = arrayListOf()

    fun setCardGroups(cardGroupsList: ArrayList<CardGroup>) {
        this.cardGroupsList = cardGroupsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder =
        CardsViewHolder(
            ItemCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {

        if (cardGroupsList[position].design_type == "HC3") {
            val type: Type = object : TypeToken<ArrayList<Int?>?>() {}.type
            val json: String? = sp.getString(ContextualCardsAdapter.DISMISS, null)
            if (!json.isNullOrEmpty())
                dismissList = Gson().fromJson(json, type);

            dismissList.forEach { cardGroupsList[position].cards.removeAt(it) }

            if (cardGroupsList[position].cards.isNullOrEmpty()) {
                cardGroupsList.removeAt(position)
                hc3CardPos = holder.adapterPosition
            }
        }

        val childLayoutManager =
            LinearLayoutManager(
                holder.binding.contextualCardsRecycler.context,
                RecyclerView.HORIZONTAL,
                false
            )

        if (hc3CardPos != position) {
            Timber.d(position.toString())
            holder.binding.contextualCardsRecycler.apply {
                layoutManager = childLayoutManager
                adapter = ContextualCardsAdapter(sp, cardGroupsList, position, dismissList)

            }
        }
    }

    override fun getItemCount(): Int = cardGroupsList.size

    class CardsViewHolder(val binding: ItemCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}