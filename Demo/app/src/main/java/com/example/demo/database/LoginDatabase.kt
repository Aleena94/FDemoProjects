package com.example.demo.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.demo.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {
    abstract val dbOperationsDao: Dao
}