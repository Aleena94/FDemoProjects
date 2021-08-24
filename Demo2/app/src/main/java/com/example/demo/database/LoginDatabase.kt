package com.example.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1)
abstract class LoginDatabase : RoomDatabase() {
    abstract fun dbOperationsDao(): Dao

    companion object {
        private var INSTANCE: LoginDatabase? = null

        fun getDatabaseClient(context: Context): LoginDatabase? {
            if (INSTANCE == null) {
                synchronized(LoginDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LoginDatabase::class.java,
                        "LOGIN_DATABASE"
                    ).build()
                }
            }
            return INSTANCE
        }

    }
}