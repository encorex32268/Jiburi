package com.lihan.jiburi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FilmDto(
    val id: String,
    @SerialName("original_title")
    val title: String,
    @SerialName("title")
    val titleEn: String,
    @SerialName("original_title_romanised")
    val titleRoma: String,
    @SerialName("image")
    val imgUrl: String,
    @SerialName("movie_banner")
    val imgUrlBanner: String,
    val director: String,
    val producer: String,
    val description: String,
    @SerialName("release_date")
    val releaseDate: String,
    @SerialName("running_time")
    val runningTime: String,
    @SerialName("rt_score")
    val score: String,
)
