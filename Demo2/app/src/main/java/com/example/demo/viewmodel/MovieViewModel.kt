package com.example.demo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.demo.model.movielist.MovieList
import com.example.demo.repository.MovieListRepository

class MovieViewModel:ViewModel(){
private var servicesLiveData: MutableLiveData<MovieList>? = null
private val repository: MovieListRepository = MovieListRepository()

fun getMovie(page: Int): LiveData<MovieList>? {
    servicesLiveData = repository.getMovies(page)
    return servicesLiveData
}
}