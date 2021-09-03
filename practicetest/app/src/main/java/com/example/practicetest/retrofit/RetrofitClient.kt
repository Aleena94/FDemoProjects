package com.example.demo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient







object RetrofitClient {

    private var BASE_URL = "https://jsonplaceholder.typicode.com/"
    private var retrofitClient: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        if (retrofitClient == null) {
            retrofitClient = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofitClient
    }



}
