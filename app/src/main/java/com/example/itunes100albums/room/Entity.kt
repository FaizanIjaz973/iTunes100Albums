package com.example.itunes100albums.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums")
data class Entity(
    val imageUrl: String,
    val name: String,
    var artistName: String,
    val price: String,
    val releaseDate: String,
    val albumUrl: String,
    //@PrimaryKey(autoGenerate = true)
    //val id : Int? = null
    @PrimaryKey()
    val id: Int
)
