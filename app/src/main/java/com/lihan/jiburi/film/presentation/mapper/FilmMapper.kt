package com.lihan.jiburi.film.presentation.mapper

import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.presentation.model.FilmUi

fun Film.toFilmUi(): FilmUi {
    return FilmUi(
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