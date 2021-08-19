package com.example.demo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.demo.model.login.LoginModel

@Dao
interface Dao {
    @Insert
    suspend fun insertData(loginTableModel: LoginModel)

    @Query("SELECT * FROM Login WHERE Username =:username")
    fun getLoginDetails(username: String?): LiveData<LoginModel>

    @Query("DELETE FROM Login WHERE username = :username")
    fun deleteByUsername(username: String?)
}