package com.example.itunes100albums.model

data class Feed(
    val author: Author,
    val entry: List<Entry>,
    val icon: Icon,
    val id: IdX,
    val link: List<LinkX>,
    val rights: RightsX,
    val title: TitleX,
    val updated: Updated
)