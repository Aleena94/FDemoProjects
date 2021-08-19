package com.example.demo.view

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.adapter.MusicListAdapter
import com.example.demo.databinding.ActivityDatalistBinding
import com.example.demo.interfaces.ItemClickListener
import com.example.demo.model.musiclist.Album
import com.example.demo.services.isOnline
import com.example.demo.viewmodel.LoginViewModel
import com.example.demo.viewmodel.MusicListViewModel
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class DataListingActivity : AppCompatActivity(), ItemClickListener {

    lateinit var context: Context
    private lateinit var musicListViewModel: MusicListViewModel
    private var adapter: MusicListAdapter? = null
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var mainBinding: ActivityDatalistBinding
    private lateinit var loginViewModel: LoginViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityDatalistBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar!!.hide()
        context = this@DataListingActivity
        layoutManager = LinearLayoutManager(this)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        musicListViewModel = ViewModelProvider(this).get(MusicListViewModel::class.java)
        if (isOnline(context)) {
            musicListViewModel.getMusic()!!.observe(this, { musicList ->

                if (musicList != null) {
                    mainBinding.recyclerView.layoutManager =
                        LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    adapter = MusicListAdapter(this, musicList.results.albummatches.album, this)
                    mainBinding.recyclerView.adapter = adapter
                    adapter!!.setMusicList(musicList.results.albummatches.album)
                    mainBinding.progressBar.visibility = View.GONE
                }

            })
        } else {
            mainBinding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Check your Network Connection", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(album: Album, url: String?) {

        val thread = Thread {
            try {
                getBitmapFromURL(url)?.let { loginViewModel.copyFileToInternalStorage(context, it) }
                val intent = Intent(this, LoadMusicUrlActivity::class.java)
                intent.putExtra("music_url", album.url)
                startActivity(intent)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()
    }

    private fun getBitmapFromURL(url: String?): Bitmap? {
        return try {
            val urlString = URL(url)
            val connection: HttpURLConnection = urlString
                .openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            Log.d("vk21", e.toString())
            null
        }
    }
}
