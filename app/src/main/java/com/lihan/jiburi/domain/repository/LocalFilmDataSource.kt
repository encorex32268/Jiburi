package com.lihan.jiburi.domain.repository

import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.flow.Flow

interface LocalFilmDataSource {
    fun getFilms(): Flow<List<Film>>
    suspend fun upsertFilms(films: List<Film>): Result<Unit, DataError.Local>
    suspend fun deleteAllFilms()
}