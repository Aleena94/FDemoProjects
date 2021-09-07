package com.example.demo.view

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.demo.R
import com.example.demo.databinding.ActivityLoginSuccessBinding
import com.example.demo.services.BroadCastReceivers
import com.example.demo.services.DownloadService
import com.example.demo.services.NotificationWorker
import com.example.demo.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


class LoginSuccessActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginSuccessBinding
    private lateinit var strUsername: String
    private val loginViewModel: LoginViewModel by inject()
    lateinit var context: Context
    private lateinit var receiver: BroadCastReceivers
    private var isPlaying = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginSuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)
        context = this@LoginSuccessActivity
        supportActionBar!!.hide()

        strUsername = intent.getStringExtra("username").toString()
        receiver = BroadCastReceivers()


        val animation = CircleAnimation(binding.viewCircle, 360)
        animation.duration = 1000
        binding.viewCircle.startAnimation(animation)
        val mWorkManager = WorkManager.getInstance()
        val mRequest = OneTimeWorkRequest.Builder(NotificationWorker::class.java).build()

        binding.btnWork.setOnClickListener {
            mWorkManager.enqueue(mRequest)
        }

        binding.imgPlayPause.setOnClickListener {
            if (!isPlaying) {
                startService(Intent(this, DownloadService::class.java))
                isPlaying = true
                binding.imgPlayPause.setImageResource(R.drawable.ic_pause)
            } else if (isPlaying) {
                isPlaying = false
                binding.imgPlayPause.setImageResource(R.drawable.ic_play)
                stopService(Intent(this, DownloadService::class.java))
            }

        }

        binding.imgCamera.setOnClickListener {
            val intent = Intent(this, ImageCaptureActivity::class.java)
            startActivity(intent)
        }

        loginViewModel.getLoginDetails(strUsername)!!.observe(this, {

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
                loginViewModel.deleteUser(strUsername)
                withContext(Dispatchers.Main) {
                    binding.txtName.text = resources.getString(R.string.user_deleted)
                }
            }
        }

        binding.btnNext.setOnClickListener {
            val intent = Intent(this, DataListingActivity::class.java)
            startActivity(intent)
        }

        binding.btnMovie.setOnClickListener {
            val intent = Intent(this, MoviesActivity::class.java)
            startActivity(intent)
        }

        binding.btnLoadMap.setOnClickListener {
            val intent = Intent(this, MapActivity::class.java)
            startActivity(intent)
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
        stopService(Intent(this, DownloadService::class.java))
    }

}