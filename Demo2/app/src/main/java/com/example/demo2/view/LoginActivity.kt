package com.example.demo2.view

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

        val action: String? = intent?.action
        val data: Uri? = intent?.data
        if(data!=null){
            val params: List<String> = data.pathSegments
            val id = params[params.size - 1]
            Toast.makeText(applicationContext,id,Toast.LENGTH_SHORT).show()
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
    }
}