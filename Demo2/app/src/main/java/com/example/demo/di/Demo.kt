package com.example.demo.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Demo : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Demo)
            modules(listOf(netmodule, database, apiInterface, repository, viewModel))
        }

    }
}