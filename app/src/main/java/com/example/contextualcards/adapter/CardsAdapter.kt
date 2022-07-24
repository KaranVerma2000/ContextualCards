package com.example.contextualcards.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.adapter.ContextualCardsAdapter.Companion.ID
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

    var dismissListMap: HashMap<Int, ArrayList<Int>> = HashMap()

    fun setCardGroups(cardGroupsList: ArrayList<CardGroup>) {
        this.cardGroupsList = cardGroupsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardsViewHolder =
        CardsViewHolder(
            ItemCardLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: CardsViewHolder, position: Int) {
        val storedId = sp.getInt(ID, -1)
        if (cardGroupsList[position].design_type == "HC3" && storedId == cardGroupsList[position].id) {

            val type: Type = object : TypeToken<HashMap<Int, ArrayList<Int>>?>() {}.type
            val json: String? = sp.getString(ContextualCardsAdapter.DISMISS, null)
            if (!json.isNullOrEmpty())
                dismissListMap = Gson().fromJson(json, type);

            Timber.d("DismissMap $dismissListMap")

            dismissListMap.forEach {
                if (it.key == storedId)
                    it.value.forEach { pos ->
                        cardGroupsList[position].cards.removeAt(pos)
                    }
            }

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
                adapter = ContextualCardsAdapter(sp, cardGroupsList, position, dismissListMap)

            }
        }
    }

    override fun getItemCount(): Int = cardGroupsList.size

    class CardsViewHolder(val binding: ItemCardLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)
}