package com.example.practicetest.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PracticeTest : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PracticeTest)
            modules(listOf(netmodule, database, apiInterface, repository, viewModel))
        }

    }
}