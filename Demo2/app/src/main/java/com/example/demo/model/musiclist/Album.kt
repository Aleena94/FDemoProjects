package com.example.demo.model.musiclist

data class Album(
    val artist: String,
    val image: List<Image>,
    val mid: String,
    val name: String,
    val streamable: String,
    val url: String
)