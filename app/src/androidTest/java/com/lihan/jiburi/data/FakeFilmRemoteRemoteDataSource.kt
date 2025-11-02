package com.lihan.jiburi.data

import com.lihan.jiburi.core.data.model.FilmDto
import com.lihan.jiburi.film.data.mapper.toFilm
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSource
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result


class FakeFilmRemoteRemoteDataSource: FilmRemoteDataSource {

    var isSuccess: Boolean = false

    override suspend fun getFilms(): Result<List<Film>, DataError.Network> {
        return if (isSuccess){
            Result.Success(
                getFilmsDto().map { it.toFilm() }
            )
        }else{
            Result.Error(
                DataError.Network.NO_INTERNET
            )
        }
    }

    private fun getFilmsDto(): List<FilmDto>{
        return (0..20).map {
            FilmDto(
                id = "idTest${it}",
                title = "Title ${it}",
                titleEn = "TitleEn ${it}",
                titleRoma = "TitleRoma ${it}",
                imgUrl = "Url${it}",
                imgUrlBanner = "UrlBanner${it}",
                director = "director${it}",
                producer = "producer${it}",
                description = "description${it}",
                releaseDate = "${1990 + it}",
                runningTime = "${100 + it}",
                score = "${50 + it}"
            )
        }
    }
}

