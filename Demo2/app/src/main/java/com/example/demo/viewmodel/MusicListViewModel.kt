package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.model.musiclist.MusicList
import com.example.demo.repository.MusicListRepository

class MusicListViewModel : ViewModel() {

    private var servicesLiveData: MutableLiveData<MusicList>? = null

    fun getMusic() : LiveData<MusicList>? {
        servicesLiveData = MusicListRepository.getServicesApiCall()
        return servicesLiveData
    }

}