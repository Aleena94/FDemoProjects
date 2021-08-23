package com.example.demo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var BASE_URL = "https://ws.audioscrobbler.com/"
    private var BASE_URL2="https://api.themoviedb.org/"
    private var retrofitClient: Retrofit? = null
    private var retrofitClient2:Retrofit?=null

    fun getRetrofitInstance(): Retrofit? {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }

    fun getRetrofitInstance2(): Retrofit? {
        if (retrofitClient2 == null) {
            retrofitClient2 = Retrofit.Builder()
                .baseUrl(BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient2
    }


}
