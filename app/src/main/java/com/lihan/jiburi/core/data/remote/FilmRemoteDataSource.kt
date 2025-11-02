package com.lihan.jiburi.core.data.remote

import com.lihan.jiburi.core.data.model.FilmDto
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result

interface FilmRemoteDataSource {
    suspend fun getFilms(): Result<List<FilmDto>, DataError.Network>
}