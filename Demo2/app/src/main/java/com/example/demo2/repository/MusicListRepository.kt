package com.example.demo2.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demo2.model.MusicList
import com.example.demo2.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MusicListRepository {

    val serviceSetterGetter = MutableLiveData<MusicList>()

    fun getServicesApiCall(): MutableLiveData<MusicList> {

        val call = RetrofitClient.apiInterface.getMusicList("album.search","believe","3a798e0cc7e3cbcaddd749a6f51a308f","json")

        call!!.enqueue(object: Callback<MusicList?> {
            override fun onFailure(call: Call<MusicList?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<MusicList?>, response: Response<MusicList?>) {
                Log.v("DEBUG : ", response.body().toString())

                serviceSetterGetter.postValue(response.body())

            }
        })

        return serviceSetterGetter
    }
}