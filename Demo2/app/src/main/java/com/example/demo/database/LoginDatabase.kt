package com.example.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {
    abstract val dbOperationsDao: Dao
}