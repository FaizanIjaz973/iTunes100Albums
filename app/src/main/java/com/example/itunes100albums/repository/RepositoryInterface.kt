package com.example.itunes100albums.repository

import com.example.itunes100albums.model.ApiResponse

interface RepositoryInterface {

    suspend fun searchAlbum() : ApiResponse?

}