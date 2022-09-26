package com.example.itunes100albums.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes100albums.R
import com.example.itunes100albums.adapter.FavoritesFragmentRecyclerViewAdapter
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import com.example.itunes100albums.databinding.FragmentFavoritesBinding
import com.example.itunes100albums.databinding.FragmentMainBinding
import com.example.itunes100albums.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class FavoritesFragment @Inject constructor(
    private val favoritesRecyclerViewAdapter: FavoritesFragmentRecyclerViewAdapter
    ) : Fragment(R.layout.fragment_favorites) {

    private val viewModel: MainViewModel by viewModels()
    private var fragmentBinding : FragmentFavoritesBinding? = null

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            viewModel.deleteFavoriteAlbum(favoritesRecyclerViewAdapter.albums[viewHolder.layoutPosition])
            favoritesRecyclerViewAdapter.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentFavoritesBinding.bind(view)
        fragmentBinding = binding

        binding.recyclerViewFavoriteAlbums.adapter = favoritesRecyclerViewAdapter
        binding.recyclerViewFavoriteAlbums.layoutManager = LinearLayoutManager(requireContext())

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewFavoriteAlbums)

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        viewModel.albumsFromDB.observe(viewLifecycleOwner, Observer {
            favoritesRecyclerViewAdapter.albums = it
        })
    }

    override fun onDestroy() {
        fragmentBinding = null
        super.onDestroy()
    }
}