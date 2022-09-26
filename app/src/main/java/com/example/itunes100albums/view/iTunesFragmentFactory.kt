package com.example.itunes100albums.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.itunes100albums.adapter.FavoritesFragmentRecyclerViewAdapter
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class iTunesFragmentFactory @Inject constructor(
    private val mainRecyclerViewAdapter: MainRecyclerViewAdapter,
    private val favoritesFragmentRecyclerViewAdapter: FavoritesFragmentRecyclerViewAdapter
    ): FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name -> MainFragment(mainRecyclerViewAdapter)
            FavoritesFragment::class.java.name -> FavoritesFragment(favoritesFragmentRecyclerViewAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}