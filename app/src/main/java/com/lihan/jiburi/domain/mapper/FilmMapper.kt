package com.lihan.jiburi.domain.mapper

import com.lihan.jiburi.data.local.entity.FilmEntity
import com.lihan.jiburi.data.model.FilmDto
import com.lihan.jiburi.domain.model.Film

fun FilmDto.toFilm(): Film {
    return Film(
        id = id,
        title = title,
        titleEn = titleEn,
        titleRoma = titleRoma,
        imgUrl = imgUrl,
        imgUrlBanner = imgUrlBanner,
        description = description,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        score = score,
        runningTime = runningTime
    )
}

fun FilmEntity.toFilm(): Film{
    return Film(
        id = id,
        title = title,
        titleEn = titleEn,
        titleRoma = titleRoma,
        imgUrl = imgUrl,
        imgUrlBanner = imgUrlBanner,
        description = description,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        score = score,
        runningTime = runningTime
    )
}

fun Film.toFilmEntity(): FilmEntity{
    return FilmEntity(
        id = id,
        title = title,
        titleEn = titleEn,
        titleRoma = titleRoma,
        imgUrl = imgUrl,
        imgUrlBanner = imgUrlBanner,
        description = description,
        director = director,
        producer = producer,
        releaseDate = releaseDate,
        score = score,
        runningTime = runningTime
    )
}