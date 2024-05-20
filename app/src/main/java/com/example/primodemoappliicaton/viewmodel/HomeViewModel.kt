package com.example.primodemoappliicaton.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primodemoappliicaton.https.ApiClient
import com.example.primodemoappliicaton.https.ApiService
import com.example.primodemoappliicaton.model.Feed
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.repository.FeedRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val repository: FeedRepository,
): ViewModel() {

    var event = MutableStateFlow<HomeViewModelEvent?>(null)
    val allFeedItems: LiveData<List<FeedItem>> = repository.allFeedItems

    init {
        viewModelScope.launch {
            if(repository.getFeedItemsCount() == 0) {
                event.value = HomeViewModelEvent.Loading
                loadData()
            } else {
                loadData(isUpdate = true)
            }
        }
    }

    private fun loadData(isUpdate: Boolean = false) {
        val apiService = ApiClient.client.create(ApiService.FeedApi::class.java)
        val call = apiService.getFeed()
        call.enqueue(object : Callback<Feed> {
            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                event.value = HomeViewModelEvent.Success

                if (response.isSuccessful && response.body() != null) {
                    val feed = response.body()

                    if(isUpdate) {
                        feed?.channel?.items?.forEach {
                            insertOrUpdateFeedItem(it)
                        }
                    } else {
                        feed?.channel?.items?.let {
                            insertAll(it)
                        }
                    }

                } else {
                    val message = response.errorBody()?.string() ?: "Error"
                    event.value = HomeViewModelEvent.Error(message)
                }
            }

            override fun onFailure(call: Call<Feed>, t: Throwable) {
                val message = t.message ?: "Error"
                event.value = HomeViewModelEvent.Error(message)
            }
        })
    }

    private fun insertAll(feedItems: List<FeedItem>) = viewModelScope.launch {
        repository.insertAll(feedItems)
    }

    private fun insertOrUpdateFeedItem(feedItems: FeedItem) = viewModelScope.launch {
        repository.insertFeedItemIfNotExists(feedItems)
    }
}

sealed class HomeViewModelEvent {
    object Loading: HomeViewModelEvent()
    object Success: HomeViewModelEvent()
    data class Error(val message: String): HomeViewModelEvent()
}