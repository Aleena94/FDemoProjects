package com.example.demo2.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demo2.R
import com.example.demo2.adapter.MusicListAdapter
import com.example.demo2.databinding.ActivityMainBinding
import com.example.demo2.services.isOnline
import com.example.demo2.viewmodel.MusicListViewModel

class MainActivity : AppCompatActivity() {

    lateinit var context: Context
    lateinit var musicListViewModel: MusicListViewModel
    private var adapter: MusicListAdapter? = null
    lateinit var layoutManager : LinearLayoutManager
    lateinit var mainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        context = this@MainActivity
        layoutManager = LinearLayoutManager(this)
        musicListViewModel = ViewModelProvider(this).get(MusicListViewModel::class.java)
            if(isOnline(context)) {
                musicListViewModel.getMusic()!!.observe(this, Observer { musicList ->

                    if (musicList != null) {
                        mainBinding.recyclerView.layoutManager =
                            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                        adapter = MusicListAdapter(this, musicList.results.albummatches.album)
                        mainBinding.recyclerView.adapter = adapter
                        adapter!!.setmusicList(musicList.results.albummatches.album)
                        mainBinding.progressBar.visibility = View.GONE
                    }

                })
            }else{
                mainBinding.progressBar.visibility = View.GONE
                Toast.makeText(this,"Check your Network Connection",Toast.LENGTH_SHORT).show()
            }
    }
}
