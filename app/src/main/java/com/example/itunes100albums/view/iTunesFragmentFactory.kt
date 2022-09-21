package com.example.itunes100albums.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.example.itunes100albums.adapter.MainRecyclerViewAdapter
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class iTunesFragmentFactory @Inject constructor(private val glide: RequestManager): FragmentFactory() {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            MainFragment::class.java.name -> MainFragment(MainRecyclerViewAdapter(glide))
            else -> super.instantiate(classLoader, className)
        }
    }
}