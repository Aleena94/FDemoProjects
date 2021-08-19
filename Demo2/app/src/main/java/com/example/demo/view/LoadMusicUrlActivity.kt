package com.example.demo.view

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.demo.databinding.ActivityLoadMusicUrlBinding

class LoadMusicUrlActivity : AppCompatActivity() {
    private lateinit var activityLoadMusicUrlBinding: ActivityLoadMusicUrlBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoadMusicUrlBinding = ActivityLoadMusicUrlBinding.inflate(layoutInflater)
        setContentView(activityLoadMusicUrlBinding.root)
        supportActionBar!!.hide()
        activityLoadMusicUrlBinding.webViewMusic.settings.javaScriptEnabled
        val musicUrl = intent.getStringExtra("music_url").toString()
        activityLoadMusicUrlBinding.webViewMusic.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }
        activityLoadMusicUrlBinding.webViewMusic.loadUrl(musicUrl)
    }
}