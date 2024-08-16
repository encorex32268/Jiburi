package com.lihan.jiburi.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
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
