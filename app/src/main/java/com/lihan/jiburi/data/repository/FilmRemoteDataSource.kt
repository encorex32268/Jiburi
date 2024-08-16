package com.lihan.jiburi.data.repository

import com.lihan.jiburi.data.model.FilmDto
import com.lihan.jiburi.data.network.get
import com.lihan.jiburi.domain.mapper.toFilm
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.repository.FilmDataSource
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import com.lihan.jiburi.domain.util.map
import io.ktor.client.HttpClient

class FilmRemoteDataSource(
    private val httpClient: HttpClient
): FilmDataSource {
    override suspend fun getFilms(): Result<List<Film>, DataError.Network> {
        return httpClient.get<List<FilmDto>>(
            route = "/films"
        ).map {
            it.map {
                it.toFilm()
            }
        }
    }
}