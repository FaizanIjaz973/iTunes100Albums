package com.example.itunes100albums.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.itunes100albums.model.ApiResponse
import com.example.itunes100albums.repository.RepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: RepositoryInterface) : ViewModel() {

    val response = MutableLiveData<ApiResponse>()
    fun searchAlbums() {
        viewModelScope.launch {
            response.postValue(repo.searchAlbum())
        }
    }
}