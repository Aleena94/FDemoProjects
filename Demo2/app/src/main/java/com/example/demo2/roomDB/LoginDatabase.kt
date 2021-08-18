package com.example.demo2.roomDB

import android.content.Context
import androidx.room.*
import com.example.demo2.model.login.LoginModel

@Database(entities = [LoginModel::class], version = 1, exportSchema = false)
abstract class LoginDatabase : RoomDatabase() {

    abstract fun loginDao() : DAO

    companion object {

        @Volatile
        private var INSTANCE: LoginDatabase? = null

        fun getDatabaseClient(context: Context) : LoginDatabase {

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