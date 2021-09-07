package com.example.demo.view

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.example.demo.R
import com.example.demo.databinding.ActivityLoginBinding
import com.example.demo.viewmodel.LoginViewModel
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by inject()
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


        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            val token = task.result
            val msg = getString(R.string.msg_token_fmt, token)

        })

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
                        loginViewModel.insertLoginData(strUsername, strPassword)
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