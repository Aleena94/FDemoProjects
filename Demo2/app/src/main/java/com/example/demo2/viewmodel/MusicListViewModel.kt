package com.example.demo2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo2.model.MusicList
import com.example.demo2.repository.MusicListRepository

class MusicListViewModel : ViewModel() {

    var servicesLiveData: MutableLiveData<MusicList>? = null

    fun getMusic() : LiveData<MusicList>? {
        servicesLiveData = MusicListRepository.getServicesApiCall()
        return servicesLiveData
    }

}