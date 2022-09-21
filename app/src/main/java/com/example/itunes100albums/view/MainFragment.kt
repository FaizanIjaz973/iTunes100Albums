package com.example.itunes100albums.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
import com.example.itunes100albums.R
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import com.example.itunes100albums.databinding.FragmentMainBinding
import com.example.itunes100albums.model.recyclerviewentity.Entity
import com.example.itunes100albums.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment @Inject constructor(private var albumRecyclerViewAdapter: MainRecyclerViewAdapter): Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private var fragmentBinding : FragmentMainBinding? = null

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.searchAlbums()

        val binding = FragmentMainBinding.bind(view)
        fragmentBinding = binding

        subscribeToObservers()

        binding.recyclerViewAlbums.adapter = albumRecyclerViewAdapter
        binding.recyclerViewAlbums.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewAlbums

        binding.fab.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFavoritesFragment()
            )
        }
    }

    private fun subscribeToObservers() {
        viewModel.response.observe(viewLifecycleOwner, Observer {
           Log.d("iTunes", "API response: " + it.toString() )
            if (it == null)
                return@Observer

            val entries = it.feed.entry
            var temp : Array<Entity> = arrayOf()//Entity(name = "Ava", artistName = "Lauren", year = 1971, imageUrl = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/34/81/99/348199f3-8c86-0643-5caa-549336d9ef46/4050538843088.jpg/170x170bb.png"))
            for(i in entries.indices){
                temp += Entity(entries[i].imimage[2].label, entries[i].imname.label, entries[i].imartist.label, 1971)
            }
            //= arrayListOf(Entity(name = "Ava", artistName = "Lauren", year = 1971, imageUrl = "https://is5-ssl.mzstatic.com/image/thumb/Music112/v4/34/81/99/348199f3-8c86-0643-5caa-549336d9ef46/4050538843088.jpg/170x170bb.png"))
            albumRecyclerViewAdapter.albums = temp.toList()
        })
    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}