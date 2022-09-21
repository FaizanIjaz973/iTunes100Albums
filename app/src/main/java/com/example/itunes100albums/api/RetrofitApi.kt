package com.example.itunes100albums.api

import com.example.itunes100albums.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitApi {

    @GET("/us/rss/topalbums/limit=100/json")
    suspend fun albumSearch(): Response<ApiResponse>
}