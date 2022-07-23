package com.example.contextualcards.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contextualcards.adapter.CardsAdapter
import com.example.contextualcards.databinding.ActivityMainBinding

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CardsAdapter()
            setHasFixedSize(true)
        }
    }
}