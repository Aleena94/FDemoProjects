package com.example.demo.di

import android.app.Application
import android.util.Log
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Demo:Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Demo)
            modules(listOf(netmodule, database, apiInterface, repository, viewModel))
        }

    }
}