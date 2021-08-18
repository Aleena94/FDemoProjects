package com.example.demo2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.demo2.model.login.LoginModel
import com.example.demo2.roomDB.LoginDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {
    companion object {

        private var loginDatabase: LoginDatabase? = null
        private var loginModel: LiveData<LoginModel>? = null

        private fun initializeDB(context: Context): LoginDatabase {
            return LoginDatabase.getDatabaseClient(context)
        }

        fun insertData(context: Context, username: String, password: String) {

            loginDatabase = initializeDB(context)

            CoroutineScope(Dispatchers.IO).launch {
                val loginDetails = LoginModel(username, password)
                loginDatabase!!.loginDao().insertData(loginDetails)
            }
        }

        fun getLoginDetails(context: Context, username: String) : LiveData<LoginModel>? {

            loginDatabase = initializeDB(context)
            loginModel = loginDatabase!!.loginDao().getLoginDetails(username)
            return loginModel
        }

         fun deleteByUsername(context: Context, username: String) {
            loginDatabase = initializeDB(context)
            loginDatabase!!.loginDao().deleteByUsername(username)

        }
    }
}