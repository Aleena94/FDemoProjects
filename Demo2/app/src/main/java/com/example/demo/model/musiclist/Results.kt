package com.example.demo.model.musiclist

data class Results(
    val attr: Attr,
    val albummatches: Albummatches,
    val query: OpensearchQuery,
    val itemsPerPage: String,
    val startIndex: String,
    val totalResults: String
)