package com.example.practicetest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicetest.model.ToDoModelItem
import com.example.practicetest.repository.ToDoRepository
import kotlinx.coroutines.launch

class ToDoViewModel(private val repository: ToDoRepository) : ViewModel() {

    private var toDoData: MutableLiveData<List<ToDoModelItem>>? = null
    private var toDoDataList: LiveData<List<ToDoModelItem>>? = null

    fun getToDo(): LiveData<List<ToDoModelItem>>? {
        viewModelScope.launch {
            toDoData = repository.getToDo()
        }
        return toDoData
    }

    fun insertToDoData(toDoItem: List<ToDoModelItem>) {
        viewModelScope.launch {
            repository.insertToDoData(toDoItem)
        }
    }
    fun getToDoDetails(): LiveData<List<ToDoModelItem>>? {
        toDoDataList = repository.getToDoDetails()
        return toDoDataList
    }
    fun updateFavourites(favourite: Boolean, id: Int) {
        repository.updateFavourites(favourite,id)
    }

}