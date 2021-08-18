package com.example.demo2.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.demo2.R
import com.example.demo2.model.Album

class MusicListAdapter(
    private val context: Context,
    musicList: List<Album>
) :
    RecyclerView.Adapter<MusicListAdapter.MyViewHolder>() {
    private var musicList: List<Album>

    fun setmusicList(musicList: List<Album>) {
        this.musicList = musicList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvName.setText(musicList!!.get(position).name)
        holder.tvArtist.setText(musicList!!.get(position).artist)
        Glide
            .with(context)
            .load(musicList!!.get(position).image.get(1).text)
            .centerCrop()
            .into(holder.imgArtist);
    }

    override fun getItemCount(): Int {
        return if (musicList != null) {
            musicList!!.size
        } else 0
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var tvName: TextView
        var tvArtist: TextView
        var imgArtist:ImageView

        init {
            tvName = itemView.findViewById<View>(R.id.nameView) as TextView
            tvArtist = itemView.findViewById<View>(R.id.artistView) as TextView
            imgArtist=itemView.findViewById<View>(R.id.imgAlbum) as ImageView
        }
    }



    init {
        this.musicList = musicList
    }
}
