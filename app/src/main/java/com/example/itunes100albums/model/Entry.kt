package com.example.itunes100albums.model

import com.google.gson.annotations.SerializedName

data class Entry(
    val category: Category,
    val id: Id,

    @SerializedName("im:artist")
    val imartist: ImArtist,
    @SerializedName("im:contentType")
    val imcontentType: ImContentType,
    @SerializedName("im:image")
    val imimage: List<ImImage>,
    @SerializedName("im:itemCount")
    val imitemCount: ImItemCount,
    @SerializedName("im:name")
    val imname: ImName,
    @SerializedName("im:price")
    val imprice: ImPrice,
    @SerializedName("im:releaseDate")
    val imreleaseDate: ImReleaseDate,
    val link: Link,
    val rights: RightsX,
    val title: Title
)