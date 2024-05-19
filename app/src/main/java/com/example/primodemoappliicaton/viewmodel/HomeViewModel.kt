package com.example.primodemoappliicaton.viewmodel

import androidx.lifecycle.ViewModel
import com.example.primodemoappliicaton.https.ApiClient
import com.example.primodemoappliicaton.https.ApiService
import com.example.primodemoappliicaton.model.Feed
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {
    init {
        loadData()
    }

    private fun loadData() {
        val apiService = ApiClient.client.create(ApiService.FeedApi::class.java)
        val call = apiService.getFeed()
        call.enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                if (response.isSuccessful && response.body() != null) {
                    val feed = response.body()
                } else {
                    //TODO: Handle fail
                }
            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                //TODO: Handle fail
            }
        })
    }
}