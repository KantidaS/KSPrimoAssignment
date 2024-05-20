package com.example.primodemoappliicaton.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.primodemoappliicaton.model.FeedItem

@Dao
interface FeedItemDao {
    @Query("SELECT * FROM feed_items")
    fun getAllFeedItems(): LiveData<List<FeedItem>>

    @Query("SELECT * FROM feed_items WHERE id = :id")
    suspend fun getFeedItemById(id: Int): FeedItem?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateFeedItem(feedItem: FeedItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(feedItems: List<FeedItem>)

    @Query("SELECT COUNT(*) FROM feed_items")
    suspend fun getFeedItemsCount(): Int

    @Query("SELECT COUNT(*) FROM feed_items WHERE title = :title")
    suspend fun getFeedItemCountWithTitle(title: String): Int

    @Query("DELETE FROM feed_items")
    suspend fun deleteAll()
}
