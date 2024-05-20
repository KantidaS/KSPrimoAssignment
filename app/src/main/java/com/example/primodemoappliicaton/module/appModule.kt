package com.example.primodemoappliicaton.module

import com.example.primodemoappliicaton.AppDatabase
import com.example.primodemoappliicaton.repository.FeedRepository
import com.example.primodemoappliicaton.viewmodel.ArticleViewModel
import com.example.primodemoappliicaton.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getDatabase(get()).feedItemDao() }
    single { FeedRepository(get()) }

    viewModel { HomeViewModel(get()) }
    viewModel { ArticleViewModel(get()) }
}