package com.lihan.jiburi.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class FilmEntity(
    @PrimaryKey(autoGenerate = false)
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