package com.example.itunes100albums.model

import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("im:id")
    val imid: String,
    val label: String,
    val scheme: String,
    val term: String
)