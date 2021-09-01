package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.model.musiclist.MusicList
import com.example.demo.repository.MusicListRepository
import kotlinx.coroutines.launch

class MusicListViewModel(private val repository: MusicListRepository) : ViewModel() {

    private var servicesLiveData: MutableLiveData<MusicList>? = null

    fun getMusic(): LiveData<MusicList>? {
        viewModelScope.launch {
            servicesLiveData = repository.getMusic()
        }
        return servicesLiveData
    }
}