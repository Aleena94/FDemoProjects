package com.example.demo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demo.databinding.ItemMovieBinding
import com.example.demo.model.movielist.Result

class MovieAdapter(
    musicList: List<Result>
) : RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {
    private var movieList: List<Result>
    private lateinit var binding: ItemMovieBinding


    fun setMovieList(movieList: List<Result>) {
        this.movieList = movieList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        movieList[position].apply {
            binding.tvTitle.text = title
            val language = "($original_language)"
            binding.tvLanguage.text = language
            binding.tvOverView.text = overview
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    inner class MyViewHolder(itemBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    init {
        this.movieList = musicList
    }
}
