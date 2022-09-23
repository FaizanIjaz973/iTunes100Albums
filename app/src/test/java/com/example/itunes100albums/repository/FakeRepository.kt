package com.example.itunes100albums.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.itunes100albums.model.*
import com.example.itunes100albums.room.Entity

class FakeRepository : RepositoryInterface {

    private var albums = mutableListOf<Entity>()
    private var albumsLiveData = MutableLiveData<List<Entity>> (albums)

    override suspend fun insertAlbum(album: Entity) {
        albums.add(album)
        refresh()
    }

    override suspend fun deleteAlbum(album: Entity) {
        albums.remove(album)
        refresh()
    }

    override fun getAllAlbums(): LiveData<List<Entity>> {
        return albumsLiveData
    }

    override suspend fun searchAlbum(): ApiResponse? {
        return ApiResponse(Feed
            (Author(Name(""),
            Uri("")),
            listOf(),
            Icon(""),
            IdX(""),
            listOf(),
            RightsX(""),
            TitleX(""),
            Updated("")))
    }

    private fun refresh(){
        albumsLiveData.postValue(albums)
    }

}