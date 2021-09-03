package com.example.practicetest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ToDo")
data class ToDoModelItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "completed")
    val completed: Boolean,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "userId")
    val userId: Int,
    @ColumnInfo(name = "favourite")
    val favourite: Boolean
)