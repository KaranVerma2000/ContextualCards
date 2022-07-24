package com.example.contextualcards.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contextualcards.adapter.CardsAdapter
import com.example.contextualcards.databinding.ActivityMainBinding
import com.example.contextualcards.model.CardGroup
import com.example.contextualcards.model.ResultHandler
import com.example.contextualcards.viewmodel.CardsViewModel

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
        cardAdapter = CardsAdapter()
        binding.cardRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = cardAdapter
        }
        getCards()
        observer()
    }

    private fun observer() {
        viewModel.cardGroups.observe(this) {
            when (it) {
                is ResultHandler.Success -> {
                    if (!it.result.card_groups.isNullOrEmpty()) {
                        cardAdapter.setCardGroups(it.result.card_groups as ArrayList<CardGroup>)
                    }
                }
                is ResultHandler.Loading -> {

                }
                is ResultHandler.Failure -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCards() {
        viewModel.getAllCards()
    }
}