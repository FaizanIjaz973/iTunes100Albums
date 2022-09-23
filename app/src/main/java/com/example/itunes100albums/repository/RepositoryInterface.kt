package com.example.itunes100albums.repository

import androidx.lifecycle.LiveData
import com.example.itunes100albums.model.ApiResponse
import com.example.itunes100albums.room.Entity

interface RepositoryInterface {

    //Inserting, deleting and getting data from the local database room.
    //Used in storing and fetching the favorite albums

    suspend fun insertAlbum(album:Entity)

    suspend fun deleteAlbum(album: Entity)

    fun getAllAlbums(): LiveData<List<Entity>>

    //For fetching the data from the API using retrofit
    suspend fun searchAlbum() : ApiResponse?
}