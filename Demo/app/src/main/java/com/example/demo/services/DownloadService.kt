package com.example.demo.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.demo.R

class DownloadService : Service() {
    private var mediaPlayer: MediaPlayer? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.astronaut)
    }

    override fun onStart(intent: Intent?, startId: Int) {
        mediaPlayer!!.start()
    }

    override fun onDestroy() {
        mediaPlayer!!.stop()
    }
}