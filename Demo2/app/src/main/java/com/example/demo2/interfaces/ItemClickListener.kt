package com.example.demo2.interfaces

import com.example.demo2.model.musiclist.Album

interface ItemClickListener {
    fun onItemClick(album: Album, url: String?)
}