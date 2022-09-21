package com.example.itunes100albums.repository

import android.util.Log
import com.example.itunes100albums.api.RetrofitApi
import com.example.itunes100albums.model.ApiResponse
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RepositoryImplementation @Inject constructor(private val retrofit: RetrofitApi) : RepositoryInterface {

    override suspend fun searchAlbum(): ApiResponse? {
        try {
            val response = retrofit.albumSearch()
            if (response.isSuccessful()) {
                val json = response.body()
                return response.body()!!
            } else {
                Log.e("iTunes", "Error occured while fetching data from the API")
            }
        }catch (Ex:Exception){
            Log.e("iTunes",Ex.localizedMessage)
        }
        return null
    }
}