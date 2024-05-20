package com.example.primodemoappliicaton.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.primodemoappliicaton.repository.FeedRepository
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val repository: FeedRepository,
): ViewModel() {

    fun getFeedItemById(id: Int) {
        viewModelScope.launch {
            //TODO: getFeedItemById
        }
    }

}