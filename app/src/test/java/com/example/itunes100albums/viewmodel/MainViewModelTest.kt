package com.example.itunes100albums.viewmodel

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.itunes100albums.MainCoroutineRule
import com.example.itunes100albums.getOrAwaitValueTest
import com.example.itunes100albums.repository.FakeRepository
import com.example.itunes100albums.room.Entity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking

import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup(){
        viewModel = MainViewModel(FakeRepository())
    }

    @Test
    fun insertFavoriteAlbum() {
        val album = Entity(imageUrl = "image.com",
            "FaizanAlbum",
            "Faizan",
            "$ 2.99",
            "15th Sep",
            "album.com",
            1245
        )

        val albums = viewModel.albumsFromDB.getOrAwaitValueTest()

        runBlocking {
            viewModel.insertFavoriteAlbum(album)
        }
        assertThat(albums).contains(album)
    }

    @Test
    fun deleteFavoriteAlbumTest(){
        val album = Entity(imageUrl = "image.com",
            "FaizanAlbum",
            "Faizan",
            "$ 2.99",
            "15th Sep",
            "album.com",
            1245
        )

        val albums = viewModel.albumsFromDB.getOrAwaitValueTest()

        runBlocking {
            viewModel.insertFavoriteAlbum(album)
            viewModel.deleteFavoriteAlbum(album)
        }
        assertThat(albums).doesNotContain(album)
    }

    @Test
    fun getAllAlbumsTest() {
        val album = Entity(imageUrl = "image.com",
            "FaizanAlbum",
            "Faizan",
            "$ 2.99",
            "15th Sep",
            "album.com",
            1245
        )
        viewModel.insertFavoriteAlbum(album)
        val albums = viewModel.albumsFromDB.getOrAwaitValueTest()
        assertThat(albums.size).isEqualTo(1)
    }
}