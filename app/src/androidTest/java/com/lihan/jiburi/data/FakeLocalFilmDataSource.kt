package com.lihan.jiburi.data

import com.lihan.jiburi.data.local.dao.FilmDao
import com.lihan.jiburi.data.local.entity.FilmEntity
import com.lihan.jiburi.domain.mapper.toFilm
import com.lihan.jiburi.domain.mapper.toFilmEntity
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.repository.LocalFilmDataSource
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import java.sql.SQLException

class FakeLocalFilmDataSource : LocalFilmDataSource {

    private val dbFilms = mutableListOf<FilmEntity>()

    override fun getFilms(): Flow<List<Film>> {
        return flow{
            emit(
                dbFilms.map {
                    it.toFilm()
                }
            )
        }
    }

    override suspend fun upsertFilms(films: List<Film>): Result<Unit, DataError.Local> {
        return try {
            dbFilms.clear()
            dbFilms.addAll(films.map { it.toFilmEntity() })
            Result.Success(Unit)
        }catch (e: SQLException){
            e.printStackTrace()
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteAllFilms() {
        dbFilms.clear()
    }
}