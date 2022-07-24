package com.example.contextualcards.network

import com.example.contextualcards.utils.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit


/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */

object Retrofit {
    private var retrofit: Retrofit? = null
    fun getClient(): Retrofit {
        if (retrofit == null)
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        return retrofit!!
    }
}