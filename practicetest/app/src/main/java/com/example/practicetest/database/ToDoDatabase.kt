package com.example.practicetest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practicetest.model.ToDoModelItem

@Database(entities = [ToDoModelItem::class], version = 1)
abstract class ToDoDatabase : RoomDatabase() {
    abstract val dbOperationsDao: Dao
}