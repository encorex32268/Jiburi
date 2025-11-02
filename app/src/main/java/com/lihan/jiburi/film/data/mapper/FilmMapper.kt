package com.lihan.jiburi.film.data.mapper

import com.lihan.jiburi.core.data.local.entity.FilmEntity
import com.lihan.jiburi.core.data.model.FilmDto
import com.lihan.jiburi.film.domain.model.Film

fun FilmDto.toFilm(): Film{
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