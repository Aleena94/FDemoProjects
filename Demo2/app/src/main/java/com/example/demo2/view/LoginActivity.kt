package com.example.demo2.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demo2.databinding.ActivityLoginBinding
import com.example.demo2.viewmodel.LoginViewModel
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    private lateinit var binding: ActivityLoginBinding
    private lateinit var strUsername: String
    private lateinit var strPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@LoginActivity
        supportActionBar!!.hide()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.btnLogin.setOnClickListener {

            strUsername = binding.txtUsername.text.toString().trim()
            strPassword = binding.txtPassword.text.toString().trim()

            when {
                strUsername.isEmpty() -> {
                    binding.txtUsername.error = "Enter Username"
                }
                strPassword.isEmpty() -> {
                    binding.txtPassword.error = "Enter Password"
                }
                else -> {
                    lifecycleScope.launch {
                        loginViewModel.insertData(context, strUsername, strPassword)
                    }
                    val intent = Intent(this, LoginSuccessActivity::class.java)
                    intent.putExtra("username", strUsername)
                    startActivity(intent)
                }
            }
        }
    }
}