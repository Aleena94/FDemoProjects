package com.example.demo2.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.demo2.R
import com.example.demo2.databinding.ActivityLoginSuccessBinding
import com.example.demo2.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginSuccessBinding
    lateinit var strUsername: String
    lateinit var loginViewModel: LoginViewModel
    lateinit var context:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context=this@LoginSuccessActivity
        getSupportActionBar()!!.hide();
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        strUsername = intent.getStringExtra("username").toString()

        binding.imgToSave.setImageResource(R.drawable.durdledoor)
        loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, Observer {

            if (it == null) {
                binding.txtName.text = "Data Not Found"

            }
            else {
                binding.txtName.text = "Welcome "+it.Username +" your password is "+it.Password

            }
        })

        binding.btnDelete.setOnClickListener{
            GlobalScope.launch {
                loginViewModel.deleteUser(context, strUsername)
                withContext(Dispatchers.Main){
                    binding.txtName.text = "User deleted"
                }
            }
        }
        binding.btnSave.setOnClickListener {
            loginViewModel.copyFileToInternalStorage(context,R.drawable.durdledoor)
            Toast.makeText(this, "Image saved to Internal Storage", Toast.LENGTH_SHORT).show()
        }

        binding.imgNext.setOnClickListener {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
    }
}