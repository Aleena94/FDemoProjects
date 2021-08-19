package com.example.demo.view

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.demo.R
import com.example.demo.databinding.ActivityLoginSuccessBinding
import com.example.demo.services.BroadCastReceivers
import com.example.demo.services.showSnackbar
import com.example.demo.viewmodel.LoginViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginSuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginSuccessBinding
    private lateinit var strUsername: String
    private lateinit var loginViewModel: LoginViewModel
    lateinit var context: Context
    private lateinit var receiver: BroadCastReceivers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@LoginSuccessActivity
        supportActionBar!!.hide()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        strUsername = intent.getStringExtra("username").toString()

        receiver = BroadCastReceivers()


        binding.imgCamera.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED -> {
                    it.showSnackbar(
                        it,
                        getString(R.string.permission_granted),
                        Snackbar.LENGTH_INDEFINITE,
                        getString(R.string.ok)
                    ) {}

                }

                ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.CAMERA
                ) -> {
                    it.showSnackbar(
                        it,
                        getString(R.string.permission_required),
                        Snackbar.LENGTH_INDEFINITE,
                        getString(R.string.ok)
                    ) {
                        requestPermissionLauncher.launch(
                            Manifest.permission.CAMERA
                        )
                    }
                }

                else -> {
                    requestPermissionLauncher.launch(
                        Manifest.permission.CAMERA
                    )
                }
            }

        }

        loginViewModel.getLoginDetails(context, strUsername)!!.observe(this, {

            if (it == null) {
                binding.txtName.text = resources.getString(R.string.data_not_found)

            } else {
                val result =
                    getString(R.string.welcome) +
                            it.Username + resources.getString(R.string.user_password) + it.Password
                binding.txtName.text = result

            }
        })

        binding.btnDelete.setOnClickListener {
            GlobalScope.launch {
                loginViewModel.deleteUser(context, strUsername)
                withContext(Dispatchers.Main) {
                    binding.txtName.text = resources.getString(R.string.user_deleted)
                }
            }
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, DataListingActivity::class.java)
            startActivity(intent)
        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    override fun onResume() {
        super.onResume()
        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also {
            registerReceiver(receiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(receiver)
    }
}