package com.example.contextualcards.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contextualcards.model.CardGroups
import com.example.contextualcards.model.ResultHandler
import com.example.contextualcards.repository.CardRepository
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @Author: Karan Verma
 * @Date: 23/07/22
 */
class CardsViewModel : ViewModel() {

    private val cardRepository = CardRepository()

    private val _cardGroups = MutableLiveData<ResultHandler<CardGroups>>()
    val cardGroups: LiveData<ResultHandler<CardGroups>> = _cardGroups

    fun getAllCards() {
        _cardGroups.postValue(ResultHandler.Loading)
        viewModelScope.launch {
            try {
                val res = cardRepository.getCards()
                Timber.d(res.toString())
                _cardGroups.postValue(ResultHandler.Success(res))
            } catch (e: Exception) {
                Timber.d("CatchFailure ${e.message.toString()}")
                _cardGroups.postValue(ResultHandler.Failure(e))
            }
        }
    }
}