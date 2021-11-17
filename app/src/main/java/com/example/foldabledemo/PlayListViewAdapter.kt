package com.example.foldabledemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayListViewAdapter(val items: List<Song>): RecyclerView.Adapter<PlayListViewAdapter.PlayListViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayListViewHolder {
    return PlayListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_song, parent, false))
  }
  class PlayListViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun updateItem(song: Song){
      view.findViewById<TextView>(R.id.title).text = song.title
      view.findViewById<TextView>(R.id.artist).text = song.artist
    }
  }

  override fun onBindViewHolder(holder: PlayListViewHolder, position: Int) {
    holder.updateItem(items[position])
  }

  override fun getItemCount(): Int = items.size


}