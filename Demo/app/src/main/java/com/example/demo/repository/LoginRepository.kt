package com.example.demo.repository

import androidx.lifecycle.LiveData
import com.example.demo.database.Dao
import com.example.demo.model.login.LoginModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository(private val dao: Dao) {

    private var loginModel: LiveData<LoginModel>? = null

    suspend fun insertData(username: String, password: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val loginDetails = LoginModel(username, password)
            dao.insertData(loginDetails)
        }
    }

    fun getLoginDetails(username: String): LiveData<LoginModel>? {
        loginModel = dao.getLoginDetails(username)
        return loginModel
    }

    fun deleteByUsername(username: String) {
        dao.deleteByUsername(username)

    }
}