package com.example.demo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private var BASE_URL = "https://ws.audioscrobbler.com/"
    private var retrofitClient: Retrofit? = null


    fun getRetrofitInstance(): Retrofit? {
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }


}
