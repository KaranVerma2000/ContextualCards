package com.example.contextualcards.network

import com.example.contextualcards.model.CardGroups
import com.example.contextualcards.model.ResultHandler
import retrofit2.Response
import retrofit2.http.GET

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
interface CardApi {

    @GET("v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad")
    suspend fun getCards() : Response<CardGroups>

}