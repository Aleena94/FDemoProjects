package com.example.demo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.demo.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun dbOperationsDao(): Dao

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabaseClient(context: Context): LoginDatabase {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, LoginDatabase::class.java, "LOGIN_DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}