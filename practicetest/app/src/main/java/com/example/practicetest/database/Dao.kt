package com.example.practicetest.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.practicetest.model.ToDoModelItem

@Dao
interface Dao {
    @Insert
    suspend fun insertData(toDoModel: List<ToDoModelItem>)

    @Query("SELECT * FROM ToDo")
    fun getToDo(): LiveData<List<ToDoModelItem>>

    @Query("UPDATE ToDo SET favourite=:favourite WHERE id = :id")
    fun updateFavourites(favourite: Boolean?, id: Int)
}