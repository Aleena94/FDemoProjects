package com.example.practicetest.retrofit

import com.example.practicetest.model.ToDoModelItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("todos/")
    fun getToDo(): Call<List<ToDoModelItem>>?

}