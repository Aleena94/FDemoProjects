package com.example.practicetest.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.demo.retrofit.RetrofitClient
import com.example.practicetest.database.Dao
import com.example.practicetest.model.ToDoModelItem
import com.example.practicetest.retrofit.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ToDoRepository(private val dao: Dao,private val retrofitClient: RetrofitClient) {

    private var todoModel: LiveData<List<ToDoModelItem>>? = null
    private val todoList = MutableLiveData<List<ToDoModelItem>>()

    suspend fun insertToDoData(toDoItem: List<ToDoModelItem>) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertData(toDoItem)
        }
    }


    fun getToDo()
            : MutableLiveData<List<ToDoModelItem>> {
        val service = retrofitClient.getRetrofitInstance()!!.create(ApiInterface::class.java)
        val call = service.getToDo()
        call!!.enqueue(object : Callback<List<ToDoModelItem>?> {
            override fun onFailure(call: Call<List<ToDoModelItem>?>, t: Throwable) {
                Log.v("DEBUG : ", t.message.toString())
            }

            override fun onResponse(call: Call<List<ToDoModelItem>?>, response: Response<List<ToDoModelItem>?>) {
                Log.v("DEBUG : ", response.body().toString())
                todoList.postValue(response.body())

            }
        })
        return todoList
    }

    fun getToDoDetails(): LiveData<List<ToDoModelItem>>? {
        todoModel = dao.getToDo()
        return todoModel
    }

    fun updateFavourites(favourite: Boolean, id: Int) {
        dao.updateFavourites(favourite, id)
    }
}