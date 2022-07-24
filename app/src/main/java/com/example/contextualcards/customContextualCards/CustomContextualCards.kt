package com.example.contextualcards.customContextualCards

import android.content.Context
import android.content.SharedPreferences
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contextualcards.R
import com.example.contextualcards.adapter.CardsAdapter
import com.example.contextualcards.databinding.ContextualCardContainerBinding
import com.example.contextualcards.interfaces.RefreshInteraction
import com.example.contextualcards.model.CardGroup

/**
 * @Author: Karan Verma
 * @Date: 24/07/22
 */
class CustomContextualCards @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding =
        ContextualCardContainerBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var cardAdapter: CardsAdapter

    private lateinit var refreshInteraction : RefreshInteraction

    fun setRecycler(sharedPreferences: SharedPreferences) {
        cardAdapter = CardsAdapter(sharedPreferences)
        binding.cardRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cardAdapter
        }
    }

    fun setData(cardGroups: List<CardGroup>) {
        cardAdapter.setCardGroups(cardGroups as ArrayList<CardGroup>)
    }

    fun loaderVisible() {
        binding.progress.visibility = View.VISIBLE
    }

    fun loaderGone() {
        binding.progress.visibility = View.GONE
    }

    fun handleRefresh(context: Context) {
        if(context is RefreshInteraction){
            refreshInteraction = context
        }
        binding.refresh.setColorSchemeColors(resources.getColor(R.color.yellow))
        binding.refresh.setOnRefreshListener {
            refreshInteraction.refreshCards()
            binding.refresh.isRefreshing = false
        }
    }
}