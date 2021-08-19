package com.example.demo.interfaces

import com.example.demo.model.musiclist.Album

interface ItemClickListener {
    fun onItemClick(album: Album, url: String?)
}