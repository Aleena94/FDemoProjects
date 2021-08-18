package com.example.demo2.model

data class Results(
    val attr: Attr,
    val albummatches: Albummatches,
    val query: OpensearchQuery,
    val itemsPerPage: String,
    val startIndex: String,
    val totalResults: String
)