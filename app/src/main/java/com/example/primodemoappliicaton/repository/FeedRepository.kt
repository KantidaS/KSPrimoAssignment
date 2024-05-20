package com.example.primodemoappliicaton.repository

import androidx.lifecycle.LiveData
import com.example.primodemoappliicaton.dao.FeedItemDao
import com.example.primodemoappliicaton.model.FeedItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FeedRepository(private val feedItemDao: FeedItemDao) {
    val allFeedItems: LiveData<List<FeedItem>> = feedItemDao.getAllFeedItems()

    suspend fun insertOrUpdateFeedItem(feedItem: FeedItem) {
        withContext(Dispatchers.IO) {
            feedItemDao.insertOrUpdateFeedItem(feedItem)
        }
    }

    suspend fun getFeedItemById(id: Int): FeedItem? {
        return withContext(Dispatchers.IO) {
            feedItemDao.getFeedItemById(id)
        }
    }

    suspend fun insertAll(feedItems: List<FeedItem>) {
        withContext(Dispatchers.IO) {
            feedItemDao.insertAll(feedItems)
        }
    }

    suspend fun getFeedItemsCount(): Int {
        return withContext(Dispatchers.IO) {
            feedItemDao.getFeedItemsCount()
        }
    }

    suspend fun insertFeedItemIfNotExists(feedItem: FeedItem) {
        val count = feedItemDao.getFeedItemCountWithTitle(feedItem.title)
        if (count == 0) {
            feedItemDao.insertOrUpdateFeedItem(feedItem)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            feedItemDao.deleteAll()
        }
    }
}
