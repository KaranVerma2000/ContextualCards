package com.example.contextualcards.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contextualcards.databinding.*
import com.example.contextualcards.model.CardGroup
import com.example.contextualcards.model.CardsType
import com.example.contextualcards.viewHolders.*
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class ContextualCardsAdapter(
    private val cardGroupsList: ArrayList<CardGroup>,
    private val groupPosition: Int
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC5 -> {
                Timber.d("HC5 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC6 -> {
                Timber.d("HC6 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
            is ViewholderHC9 -> {
                Timber.d("HC9 hit")
                holder.bind(cardGroupsList, position, groupPosition)
            }
        }
    }

    override fun getItemCount(): Int = cardGroupsList[groupPosition].cards.size
}