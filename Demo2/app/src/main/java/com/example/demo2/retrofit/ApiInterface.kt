package com.example.demo2.retrofit

import com.example.demo2.model.MusicList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("2.0/")
    fun getMusicList(
        @Query("method") method: String?,
        @Query("album") album: String?,
        @Query("api_key") apikey: String?,
        @Query("format") format: String?
    ): Call<MusicList?>?
}