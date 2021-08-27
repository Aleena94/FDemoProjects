package com.example.demo.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.demo.R
import com.example.demo.databinding.ActivityLoginBinding
import com.example.demo.viewmodel.LoginViewModel
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

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.DarkTheme)
        } else {
            setTheme(R.style.AppTheme)
        }

        intent?.action
        val data: Uri? = intent?.data
        if (data != null) {
            val params: List<String> = data.pathSegments
            val id = params[params.size - 1]
            Toast.makeText(applicationContext, id, Toast.LENGTH_SHORT).show()
        }

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
        binding.switchTheme.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}