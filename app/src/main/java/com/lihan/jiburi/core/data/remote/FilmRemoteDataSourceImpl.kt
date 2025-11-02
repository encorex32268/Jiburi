package com.lihan.jiburi.core.data.remote

import com.lihan.jiburi.core.data.model.FilmDto
import com.lihan.jiburi.core.data.network.get
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import io.ktor.client.HttpClient

class FilmRemoteDataSourceImpl(
    private val httpClient: HttpClient
): FilmRemoteDataSource {
    override suspend fun getFilms(): Result<List<FilmDto>, DataError.Network> {
        return httpClient.get<List<FilmDto>>(route = "/films")
    }
}