package com.lihan.jiburi.domain.repository

import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result


interface FilmsRepository {
    suspend fun getFilms(): Result<List<Film>, DataError.Network>
}