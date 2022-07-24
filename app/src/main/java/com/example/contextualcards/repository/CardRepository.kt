package com.example.contextualcards.repository

import com.example.contextualcards.model.CardGroups
import com.example.contextualcards.model.ResultHandler
import com.example.contextualcards.network.CardApi
import com.example.contextualcards.network.Retrofit
import com.example.contextualcards.network.SafeApiRequest

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class CardRepository : SafeApiRequest() {

    private val retrofit = Retrofit.getClient().create(CardApi::class.java)

    suspend fun getCards() : CardGroups {
        return apiRequest { retrofit.getCards() }
    }

}