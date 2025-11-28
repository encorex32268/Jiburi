package com.lihan.jiburi.film.domain.repository

import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.domain.model.Film

interface FilmsRepository {
    suspend fun getFilms(forceFetch: Boolean = false): Result<List<Film>, DataError.Network>
}
