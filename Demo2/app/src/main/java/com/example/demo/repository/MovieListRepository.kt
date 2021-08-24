package com.example.demo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demo.model.movielist.MovieList
import com.example.demo.retrofit.ApiInterface
import com.example.demo.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListRepository {
    val movieList = MutableLiveData<MovieList>()

    fun getMovies(page: Int): MutableLiveData<MovieList> {
        val service: ApiInterface =
            RetrofitClient.getRetrofitInstance2()!!.create(ApiInterface::class.java)
        val call =
            service.getMovieList(page.toString(), "en_US", "9b3b08faa69545763a0a0fab4646c752")

        call!!.enqueue(object : Callback<MovieList?> {
            override fun onFailure(call: Call<MovieList?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<MovieList?>, response: Response<MovieList?>) {
                Log.v("DEBUG : ", response.body().toString())
                movieList.postValue(response.body())

            }
        })

        return movieList
    }
}