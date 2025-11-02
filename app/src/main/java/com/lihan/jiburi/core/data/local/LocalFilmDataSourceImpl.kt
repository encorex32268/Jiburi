package com.lihan.jiburi.core.data.local

import android.database.sqlite.SQLiteDiskIOException
import com.lihan.jiburi.core.data.local.dao.FilmDao
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.data.mapper.toFilm
import com.lihan.jiburi.film.data.mapper.toFilmEntity
import com.lihan.jiburi.film.domain.model.Film
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.sql.SQLException

class LocalFilmDataSourceImpl(
    private val dao: FilmDao
) : LocalFilmDataSource {
    override fun getFilms(): Flow<List<Film>> {
        return dao.getFilms().map { dbFilms ->
            dbFilms.map {
                it.toFilm()
            }
        }
    }

    override fun getFilmById(id: String): Flow<Film>? {
        return dao.getFilmById(id)?.map { it.toFilm() }
    }

    override suspend fun upsertFilms(films: List<Film>): Result<Unit, DataError.Local> {
        return try {
            dao.upsertFilms(
                films = films.map {
                    it.toFilmEntity()
                }
            )
            Result.Success(Unit)
        }catch (e: SQLException){
            e.printStackTrace()
            Result.Error(DataError.Local.SQLERROR)
        }catch (e: SQLiteDiskIOException){
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteAllFilms() {
        dao.deleteAllFilms()
    }
}