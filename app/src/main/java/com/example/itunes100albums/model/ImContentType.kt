package com.example.itunes100albums.model

import com.google.gson.annotations.SerializedName

data class ImContentType(
    val attributes: AttributesXXX,
    @SerializedName("im:contentType")
    val imcontentType: ImContentTypeX
)