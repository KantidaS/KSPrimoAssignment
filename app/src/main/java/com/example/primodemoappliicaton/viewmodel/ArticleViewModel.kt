package com.example.primodemoappliicaton.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primodemoappliicaton.model.FeedItem
import com.example.primodemoappliicaton.repository.FeedRepository
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val repository: FeedRepository,
): ViewModel() {

    private val _feedItem = MutableLiveData<FeedItem?>()
    val feedItem: LiveData<FeedItem?> get() = _feedItem

    fun getFeedItemById(id: Int) {
        viewModelScope.launch {
            _feedItem.value = repository.getFeedItemById(id)
        }
    }

}