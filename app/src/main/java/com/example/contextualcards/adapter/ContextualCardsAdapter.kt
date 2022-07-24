package com.example.contextualcards.adapter

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.*
import com.example.contextualcards.model.CardGroup
import com.example.contextualcards.model.CardsType
import com.example.contextualcards.viewHolders.*
import com.google.gson.Gson
import timber.log.Timber


/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ContextualCardsAdapter(
    private val sp: SharedPreferences,
    private val cardGroupsList: ArrayList<CardGroup>,
    private val groupPosition: Int,
    private val dismissListMap: HashMap<Int, ArrayList<Int>>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var dismissList: ArrayList<Int>? = arrayListOf()

    override fun getItemViewType(position: Int): Int {
        Timber.d(cardGroupsList[groupPosition].design_type)
        return when (cardGroupsList[groupPosition].design_type) {
            "HC1" -> {
                CardsType.HC1
            }
            "HC3" -> {
                CardsType.HC3
            }
            "HC5" -> {
                CardsType.HC5
            }
            "HC6" -> {
                CardsType.HC6
            }
            "HC9" -> {
                CardsType.HC9
            }
            else -> {
                CardsType.HC1
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CardsType.HC1 -> {
                ViewholderHC1(
                    ItemHc1LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            CardsType.HC3 -> {
                ViewholderHC3(
                    ItemHc3LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            CardsType.HC5 -> {
                ViewholderHC5(
                    ItemHc5LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            CardsType.HC6 -> {
                ViewholderHC6(
                    ItemHc6LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            CardsType.HC9 -> {
                ViewholderHC9(
                    ItemHc9LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> {
                Timber.d("Else part of cards run")
                ViewholderHC9(
                    ItemHc9LayoutBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewholderHC1 -> {
                Timber.d("HC1 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC3 -> {
                Timber.d("HC3 hit")

                holder.binding.remindLaterBtn.setOnClickListener {
                    cardGroupsList[groupPosition].cards.removeAt(position)
                    notifyItemRemoved(position)
                }

                holder.binding.dismissBtn.setOnClickListener {
                    cardGroupsList[groupPosition].cards.removeAt(position)
                    notifyItemRemoved(position)

                    if (!dismissListMap[cardGroupsList[groupPosition].id].isNullOrEmpty())
                        dismissList = dismissListMap[cardGroupsList[groupPosition].id]
                    dismissList?.add(position)
                    Timber.d("Dismisslist $dismissList")

                    dismissList?.let { it ->
                        dismissListMap.put(
                            cardGroupsList[groupPosition].id,
                            it
                        )
                    }

                    sp.edit().putInt(ID, cardGroupsList[groupPosition].id).apply()
                    sp.edit().putString(DISMISS, Gson().toJson(dismissListMap)).apply()
                }

                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC5 -> {
                Timber.d("HC5 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC6 -> {
                Timber.d("HC6 hit")
                Timber.d("HC6 $position ${cardGroupsList[groupPosition].cards.size}")
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC9 -> {
                Timber.d("HC9 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return when {
            cardGroupsList[groupPosition].cards.isNullOrEmpty() -> {
                Timber.d("hc3 zero hit")
                0
            }
            cardGroupsList[groupPosition].is_scrollable -> cardGroupsList[groupPosition].cards.size
            else -> 1
        }
    }

    companion object {
        const val DISMISS = "DISMISS"
        const val ID = "ID"
    }
}