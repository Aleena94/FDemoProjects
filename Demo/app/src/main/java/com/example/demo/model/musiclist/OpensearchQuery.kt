package com.example.demo.model.musiclist

data class OpensearchQuery(
    val text: String,
    val role: String,
    val searchTerms: String,
    val startPage: String
)