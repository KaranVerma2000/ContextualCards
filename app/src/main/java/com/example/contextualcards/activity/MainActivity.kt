package com.example.contextualcards.activity

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.contextualcards.R
import com.example.contextualcards.databinding.ActivityMainBinding
import com.example.contextualcards.interfaces.RefreshInteraction
import com.example.contextualcards.model.ResultHandler
import com.example.contextualcards.viewmodel.CardsViewModel
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class MainActivity : AppCompatActivity(), RefreshInteraction {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CardsViewModel

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
        binding.customCards.setRecycler(sharedPreferences)
        binding.customCards.handleRefresh(this)
        getCards()
        observer()

    }

    private fun observer() {
        viewModel.cardGroups.observe(this) {
            when (it) {
                is ResultHandler.Success -> {
                    binding.customCards.loaderGone()
                    if (!it.result.card_groups.isNullOrEmpty()) {
                        Timber.d("${it.result.card_groups.size}")
                        binding.customCards.setData(it.result.card_groups)
                    }
                }
                is ResultHandler.Loading -> {
                    binding.customCards.loaderVisible()
                }
                is ResultHandler.Failure -> {
                    binding.customCards.loaderGone()
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun getCards() {
        viewModel.getAllCards()
    }

    override fun refreshCards() {
        Timber.d("refresh hit")
        viewModel.getAllCards()
    }
}