package com.example.foldabledemo

import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.window.layout.WindowInfoRepository
import androidx.window.layout.WindowInfoRepository.Companion.windowInfoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainFragment: Fragment() {
  private lateinit var windowInfoRepository: WindowInfoRepository

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    windowInfoRepository = requireActivity().windowInfoRepository()

    lifecycleScope.launch(Dispatchers.Main) {
      lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        windowInfoRepository.windowLayoutInfo.collect {
          view?.findViewById<SplitLayout>(R.id.split_layout)?.updateWindowLayout(it)
          if(it.displayFeatures.isEmpty()) { // Folded State
            showPlayListButton()
          } else {
            hidePlayListButton()
          }
        }
      }
    }
  }

  fun hidePlayListButton() {
    view?.findViewById<ImageView>(R.id.list_button)?.visibility = View.GONE
  }

  fun showPlayListButton() {
    view?.findViewById<ImageView>(R.id.list_button)?.visibility = View.VISIBLE
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.main_fragment, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    activity?.supportFragmentManager?.beginTransaction()
      ?.setReorderingAllowed(true)
      ?.replace(R.id.end_layout, PlayListFragment.newInstance())
      ?.commit()

    view.findViewById<ImageView>(R.id.list_button).setOnClickListener {
      activity?.supportFragmentManager?.beginTransaction()
        ?.setReorderingAllowed(true)
        ?.replace(R.id.fragment_container_view, PlayListFragment.newInstance())
        ?.addToBackStack(null)
        ?.commit()
    }
  }


  companion object{
    fun newInstance(): MainFragment{
      return MainFragment()
    }
  }
}