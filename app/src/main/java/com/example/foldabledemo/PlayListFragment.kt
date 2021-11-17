package com.example.foldabledemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PlayListFragment: Fragment() {
  companion object{
    fun newInstance() = PlayListFragment()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.playlist_view, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

      with(view.findViewById<RecyclerView>(R.id.rv_view)){
        layoutManager = LinearLayoutManager(requireContext())
        adapter = PlayListViewAdapter(
          listOf(
            Song("Music 1", "Hello"),
            Song("Music 2", "Lolipop"),
            Song("Firework", "Jelly"),
            Song("Chemical", "Jelly"),
            Song("Djakarta", "Charles"),
            Song("Narnia", "Tom")
          )
        )
      }
  }
}