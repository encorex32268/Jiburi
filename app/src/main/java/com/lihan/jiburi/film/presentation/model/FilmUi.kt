package com.lihan.jiburi.film.presentation.model

data class FilmUi(
    val id: String,
    val title:String,
    val titleEn: String,
    val titleRoma: String,
    val imgUrl: String,
    val imgUrlBanner: String,
    val description: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val score: String,
    val runningTime: String
)
