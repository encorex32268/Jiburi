package com.lihan.jiburi.core.data.local

import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface LocalFilmDataSource {
    fun getFilms(): Flow<List<Film>>
    fun getFilmById(id: String): Flow<Film>?
    suspend fun upsertFilms(films: List<Film>): Result<Unit, DataError.Local>
    suspend fun deleteAllFilms()
}