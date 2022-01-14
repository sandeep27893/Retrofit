package com.example.retrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.retrofit.api.QuoteService
import com.example.retrofit.models.QuotesList

class QuoteRepository(private val quoteService: QuoteService) {

    private val quoteLiveData = MutableLiveData<QuotesList>()

    val quotes : LiveData<QuotesList>
    get() = quoteLiveData

    suspend fun getQuotes(page :Int){
        val result = quoteService.getQuotes(page)

        if(result.body() != null){
                quoteLiveData.postValue(result.body())
        }
    }
}