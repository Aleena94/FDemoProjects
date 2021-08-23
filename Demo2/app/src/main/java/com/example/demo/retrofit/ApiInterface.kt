package com.example.demo.retrofit

import com.example.demo.model.movielist.MovieList
import com.example.demo.model.musiclist.MusicList
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


    @GET("3/movie/top_rated")
    fun getMovieList(
        @Query("page") page: String?,
        @Query("language") language: String?,
        @Query("api_key") apikey: String?,
    ): Call<MovieList?>?

}