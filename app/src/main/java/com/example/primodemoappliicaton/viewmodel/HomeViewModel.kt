package com.example.primodemoappliicaton.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primodemoappliicaton.https.ApiClient
import com.example.primodemoappliicaton.https.ApiService
import com.example.primodemoappliicaton.model.Feed
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.repository.FeedRepository
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val repository: FeedRepository,
): ViewModel() {

    val allFeedItems: LiveData<List<FeedItem>> = repository.allFeedItems

    var title = mutableStateOf("")
    var isLoading = mutableStateOf(false)


    init {
        viewModelScope.launch {
            if(repository.getFeedItemsCount() == 0) {
                loadData()
            } else {
                //TODO: Update
            }
        }
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

                        channel.items?.let {
                            if(it.isNotEmpty()) {
                                insertAll(it)
                            }
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

    private fun insertAll(feedItems: List<FeedItem>) = viewModelScope.launch {
        repository.insertAll(feedItems)
    }
}