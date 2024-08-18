package com.lihan.jiburi.data

import com.lihan.jiburi.data.model.FilmDto
import com.lihan.jiburi.data.network.get
import com.lihan.jiburi.domain.mapper.toFilm
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.repository.FilmDataSource
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import com.lihan.jiburi.domain.util.map
import io.ktor.client.HttpClient
import kotlinx.coroutines.delay



class FakeFilmRemoteDataSource: FilmDataSource {

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

