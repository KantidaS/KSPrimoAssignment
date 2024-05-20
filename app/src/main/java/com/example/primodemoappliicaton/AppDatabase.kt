package com.example.primodemoappliicaton

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.example.primodemoappliicaton.dao.FeedItemDao
import com.example.primodemoappliicaton.model.FeedItem

@Database(entities = [FeedItem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedItemDao(): FeedItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "feed_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
