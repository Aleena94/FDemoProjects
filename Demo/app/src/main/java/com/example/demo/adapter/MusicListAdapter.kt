package com.example.demo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.demo.databinding.ItemMusiclistBinding
import com.example.demo.interfaces.ItemClickListener
import com.example.demo.model.musiclist.Album


class MusicListAdapter(
    private val context: Context,
    musicList: List<Album>,
    clickListener: ItemClickListener
) : RecyclerView.Adapter<MusicListAdapter.MyViewHolder>() {
    private var musicList: List<Album>
    private val clickListener: ItemClickListener
    private lateinit var binding: ItemMusiclistBinding


    fun setMusicList(musicList: List<Album>) {
        this.musicList = musicList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding = ItemMusiclistBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        binding.tvName.text = (musicList[position].name)
        binding.tvArtist.text = (musicList[position].artist)
        Glide
            .with(context)
            .load(musicList[position].image[1].text)
            .centerCrop()
            .into(binding.imgArtist)

        binding.itemMusic.setOnClickListener {
            clickListener.onItemClick(
                musicList[position],
                musicList[position].image[1].text
            )
        }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    inner class MyViewHolder(itemBinding: ItemMusiclistBinding) : ViewHolder(itemBinding.root)


    init {
        this.musicList = musicList
        this.clickListener = clickListener
    }
}
