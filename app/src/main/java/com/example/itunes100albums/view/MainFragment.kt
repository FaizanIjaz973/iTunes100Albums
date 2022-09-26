package com.example.itunes100albums.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunes100albums.R
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import com.example.itunes100albums.databinding.FragmentMainBinding
import com.example.itunes100albums.room.Entity
import com.example.itunes100albums.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MainFragment @Inject constructor(
    private var albumRecyclerViewAdapter: MainRecyclerViewAdapter
    ): Fragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()
    private var fragmentBinding : FragmentMainBinding? = null

    private val swipeCallBack = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            viewModel.insertFavoriteAlbum(albumRecyclerViewAdapter.albums[viewHolder.layoutPosition])
            albumRecyclerViewAdapter.notifyDataSetChanged()

            val snackbar = view?.let { it1 -> Snackbar.make(it1.findViewById(R.id.rootLayoutFragmentMain),
                "Album " + albumRecyclerViewAdapter.albums[viewHolder.layoutPosition].name + " added to favorites!",
                Snackbar.LENGTH_LONG) }
            if (snackbar != null) {
                snackbar.show()
            }
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

        ItemTouchHelper(swipeCallBack).attachToRecyclerView(binding.recyclerViewAlbums)

        binding.fab.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToFavoritesFragment()
            )
        }
    }

    private fun subscribeToObservers() {
        viewModel.response.observe(viewLifecycleOwner, Observer {
            if (it == null)
                return@Observer

            val entries = it.feed.entry
            var temp : Array<Entity> = arrayOf()
            for(i in entries.indices){
                temp += Entity(
                    imageUrl = entries[i].imimage[2].label,
                    name = entries[i].imname.label,
                    artistName = entries[i].imartist.label,
                    price = entries[i].imprice.label,
                    releaseDate = entries[i].imreleaseDate.attributes.label,
                    albumUrl = entries[i].id.label,
                    id = entries[i].id.attributes.imid.toInt())
            }
           albumRecyclerViewAdapter.albums = temp.toList()
        })

    }

    override fun onDestroyView() {
        fragmentBinding = null
        super.onDestroyView()
    }
}