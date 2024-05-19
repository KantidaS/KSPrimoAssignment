package com.example.primodemoappliicaton.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.primodemoappliicaton.https.ApiClient
import com.example.primodemoappliicaton.https.ApiService
import com.example.primodemoappliicaton.model.Feed
import com.example.primodemoappliicaton.model.FeedItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var _itemList = mutableStateListOf<FeedItem>()
    val itemList: List<FeedItem> = _itemList

    var title = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    init {
        loadData()
    }

    private fun loadData() {
        isLoading.value = true
        val apiService = ApiClient.client.create(ApiService.FeedApi::class.java)
        val call = apiService.getFeed()
        call.enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                isLoading.value = false
                if (response.isSuccessful && response.body() != null) {
                    val feed = response.body()

                    feed?.channel?.let { channel ->
                        title.value = channel.title

                        if (!channel.items.isNullOrEmpty()) {
                            _itemList.addAll(channel.items!!)
                        }
                    }

                } else {
                    //TODO: Handle fail
                }
            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                isLoading.value = false
                //TODO: Handle fail
            }
        })
    }
}