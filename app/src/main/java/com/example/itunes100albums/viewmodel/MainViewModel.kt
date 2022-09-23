package com.example.itunes100albums.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunes100albums.model.ApiResponse
import com.example.itunes100albums.repository.RepositoryInterface
import com.example.itunes100albums.room.Entity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: RepositoryInterface) : ViewModel() {

    val response = MutableLiveData<ApiResponse>()

    //Gets LiveData of all the albums in the DB
    var albumsFromDB = repo.getAllAlbums()

    fun insertFavoriteAlbum(album: Entity){
       viewModelScope.launch {
           repo.insertAlbum(album)
       }
    }

    fun deleteFavoriteAlbum(album: Entity){
        viewModelScope.launch {
            repo.deleteAlbum(album)
        }
    }

    fun searchAlbums() {
        viewModelScope.launch {
            response.postValue(repo.searchAlbum())
        }
    }
}