package com.example.itunes100albums.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.itunes100albums.api.RetrofitApi
import com.example.itunes100albums.model.ApiResponse
import com.example.itunes100albums.room.Dao
import com.example.itunes100albums.room.Entity
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(
    private val dao: Dao,
    private val retrofit: RetrofitApi
    ) : RepositoryInterface {


    override suspend fun insertAlbum(album: Entity){
        dao.insertAlbum(album)
    }

    override suspend fun deleteAlbum(album: Entity){
        dao.deleteAlbum(album)
    }

    override fun getAllAlbums(): LiveData<List<Entity>> {
        return dao.getAllAlbums()
    }

    override suspend fun searchAlbum(): ApiResponse? {
        try {
            val response = retrofit.albumSearch()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                Log.e("iTunes", "Error occurred while fetching data from the API")
            }
        }catch (Ex:Exception){
            Log.e("iTunes",Ex.localizedMessage)
        }
        return null
    }
}