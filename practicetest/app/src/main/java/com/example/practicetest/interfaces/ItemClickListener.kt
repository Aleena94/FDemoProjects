package com.example.practicetest.interfaces


interface ItemClickListener {
    fun onItemClick(favourite: Boolean, id: Int, position: Int)
}