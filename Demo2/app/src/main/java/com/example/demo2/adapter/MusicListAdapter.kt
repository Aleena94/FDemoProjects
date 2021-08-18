package com.example.demo2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.demo2.R
import com.example.demo2.interfaces.ItemClickListener
import com.example.demo2.model.musiclist.Album


class MusicListAdapter(
    private val context: Context,
    musicList: List<Album>,
    clickListener: ItemClickListener
) :
    RecyclerView.Adapter<MusicListAdapter.MyViewHolder>() {
    private var musicList: List<Album>
    private val clickListener: ItemClickListener


    fun setMusicList(musicList: List<Album>) {
        this.musicList = musicList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.musiclist_recycler_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.text=(musicList[position].name)
        holder.tvArtist.text=(musicList[position].artist)
        Glide
            .with(context)
            .load(musicList[position].image[1].text)
            .centerCrop()
            .into(holder.imgArtist)

        holder.cardItem.setOnClickListener { clickListener.onItemClick(musicList[position],
            musicList[position].image[1].text) }
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById<View>(R.id.nameView) as TextView
        var tvArtist: TextView = itemView.findViewById<View>(R.id.artistView) as TextView
        var imgArtist:ImageView = itemView.findViewById<View>(R.id.imgAlbum) as ImageView
        var cardItem:CardView = itemView.findViewById<View>(R.id.cardItem) as CardView

    }



    init {
        this.musicList = musicList
        this.clickListener = clickListener
    }
}
