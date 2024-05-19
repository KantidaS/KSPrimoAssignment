package com.example.primodemoappliicaton

import android.app.Application
import com.example.primodemoappliicaton.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                viewModelModule
            )
        }
    }
}