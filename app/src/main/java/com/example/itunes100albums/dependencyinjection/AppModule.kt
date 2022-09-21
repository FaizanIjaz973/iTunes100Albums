package com.example.itunes100albums.dependencyinjection

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.itunes100albums.R
import com.example.itunes100albums.api.RetrofitApi
import com.example.itunes100albums.repository.RepositoryImplementation
import com.example.itunes100albums.repository.RepositoryInterface
import com.example.itunes100albums.util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit() : RetrofitApi {
       return Retrofit.Builder()
           .addConverterFactory(GsonConverterFactory.create())
           .baseUrl(BASE_URL)
           .build()
           .create(RetrofitApi::class.java)
    }

    @Singleton
    @Provides
    fun injectNormalRepo(api: RetrofitApi) = RepositoryImplementation(api) as RepositoryInterface

    @Singleton
    @Provides
    fun injectGlide(@ApplicationContext context: Context) = Glide
        .with(context).setDefaultRequestOptions(
            RequestOptions().placeholder(R.drawable.ic_launcher_foreground)
                .error(R.drawable.ic_launcher_foreground)
        )
}