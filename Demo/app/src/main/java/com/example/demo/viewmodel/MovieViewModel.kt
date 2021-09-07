package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demo.model.movielist.MovieList
import com.example.demo.repository.MovieListRepository
import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieListRepository) : ViewModel() {
    private var servicesLiveData: MutableLiveData<MovieList>? = null

    fun getMovie(page: Int): LiveData<MovieList>? {
        viewModelScope.launch {
            servicesLiveData = repository.getMovies(page)
        }
        return servicesLiveData
    }
}