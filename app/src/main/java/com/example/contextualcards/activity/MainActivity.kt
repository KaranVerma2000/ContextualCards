package com.example.contextualcards.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TimePicker
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contextualcards.R
import com.example.contextualcards.adapter.CardsAdapter
import com.example.contextualcards.databinding.ActivityMainBinding
import com.example.contextualcards.model.CardGroup
import com.example.contextualcards.model.ResultHandler
import com.example.contextualcards.viewmodel.CardsViewModel
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CardsViewModel
    private lateinit var cardAdapter: CardsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[CardsViewModel::class.java]
        val sharedPreferences: SharedPreferences = getSharedPreferences(
            getString(
                R.string.app_name
            ), Context.MODE_PRIVATE
        )
        cardAdapter = CardsAdapter(sharedPreferences)
        binding.contextualCardContainer.cardRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
        }
        getCards()
        observer()
        binding.contextualCardContainer.refresh.setColorSchemeColors(resources.getColor(R.color.yellow))
        binding.contextualCardContainer.refresh.setOnRefreshListener {
            getCards()
            binding.contextualCardContainer.refresh.isRefreshing = false
        }
    }

    private fun observer() {
        viewModel.cardGroups.observe(this) {
            when (it) {
                is ResultHandler.Success -> {
                    binding.contextualCardContainer.progress.visibility = View.GONE
                    if (!it.result.card_groups.isNullOrEmpty()) {
                        Timber.d("${it.result.card_groups.size}")
                        cardAdapter.setCardGroups(it.result.card_groups as ArrayList<CardGroup>)
                    }
                }
                is ResultHandler.Loading -> {
                    binding.contextualCardContainer.progress.visibility = View.VISIBLE
                }
                is ResultHandler.Failure -> {
                    binding.contextualCardContainer.progress.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCards() {
        viewModel.getAllCards()
    }
}