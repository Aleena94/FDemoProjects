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
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo.adapter.MusicListAdapter
import com.example.demo.databinding.ActivityDatalistBinding
import com.example.demo.interfaces.ItemClickListener
import com.example.demo.model.musiclist.Album
import com.example.demo.services.isOnline
import com.example.demo.viewmodel.LoginViewModel
import com.example.demo.viewmodel.MusicListViewModel
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DataListingActivity : AppCompatActivity(), ItemClickListener {

    lateinit var context: Context
    private val musicListViewModel: MusicListViewModel by inject()
    private var adapter: MusicListAdapter? = null
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var mainBinding: ActivityDatalistBinding
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityDatalistBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        supportActionBar!!.hide()
        context = this@DataListingActivity

        layoutManager = LinearLayoutManager(this)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isOnline(context)) {
                    musicListViewModel.getMusic()!!.observe(context as DataListingActivity, { musicList ->

                        if (musicList != null) {
                            mainBinding.recyclerView.layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                            adapter =
                                MusicListAdapter(context, musicList.results.albummatches.album,
                                    context as DataListingActivity
                                )
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
            Log.d("exception", e.toString())
            null
        }
    }
}
