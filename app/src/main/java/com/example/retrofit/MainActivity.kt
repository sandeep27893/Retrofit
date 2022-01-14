package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.retrofit.api.QuoteService
import com.example.retrofit.api.RetrofitHelper
import com.example.retrofit.repository.QuoteRepository
import com.example.retrofit.viewmodels.MainViewModel
import com.example.retrofit.viewmodels.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)

        val repository = QuoteRepository(quoteService)

        mainViewModel = ViewModelProvider(this , MainViewModelFactory(repository)).get(MainViewModel::class.java)

        mainViewModel.quotes.observe(this, {

            val qouteList = it.results

            qouteList.forEach{
                Log.d("CheesyCodeData", it.content)
            }
        })
    }
}