package com.example.demo.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.demo.constants.Constants
import com.example.demo.model.musiclist.MusicList
import com.example.demo.retrofit.ApiInterface
import com.example.demo.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MusicListRepository {
    val musicList = MutableLiveData<MusicList>()

    fun getMusic(): MutableLiveData<MusicList> {
        val service: ApiInterface =
            RetrofitClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
        val call = service.getMusicList("album.search", "believe", Constants.api_key, "json")

        call!!.enqueue(object : Callback<MusicList?> {
            override fun onFailure(call: Call<MusicList?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<MusicList?>, response: Response<MusicList?>) {
                Log.v("DEBUG : ", response.body().toString())
                println("response music:" + call.request().url)
                musicList.postValue(response.body())

            }
        })

        return musicList
    }
}