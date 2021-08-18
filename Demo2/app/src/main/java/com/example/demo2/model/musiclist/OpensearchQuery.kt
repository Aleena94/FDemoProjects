package com.example.demo2.model.musiclist

data class OpensearchQuery(
    val text: String,
    val role: String,
    val searchTerms: String,
    val startPage: String
)