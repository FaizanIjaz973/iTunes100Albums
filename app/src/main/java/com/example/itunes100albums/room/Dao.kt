package com.example.itunes100albums.room

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao

@Dao
interface Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAlbum(entity: Entity)

    @Delete
    suspend fun deleteAlbum(entity: Entity)

    @Query("SELECT * FROM albums")
    fun getAllAlbums(): LiveData<List<Entity>>
}