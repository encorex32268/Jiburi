package com.lihan.jiburi.data.repository

import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSource
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import com.lihan.jiburi.core.data.local.LocalFilmDataSource
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.data.mapper.toFilm
import kotlinx.coroutines.flow.first

class FakeFilmsRepositoryImpl(
    private val filmRemoteDataSource: FilmRemoteDataSource,
    private val localFilmDataSource: LocalFilmDataSource
): FilmsRepository {
    override suspend fun getFilms(forceFetch: Boolean): Result<List<Film>, DataError.Network> {
        val localData = localFilmDataSource.getFilms().first()
        val shouldFetch = localData.isEmpty() || forceFetch

        return if (shouldFetch) {
            when(val result = filmRemoteDataSource.getFilms()){
                is Result.Error -> {
                    if (localData.isNotEmpty()){
                        Result.Success(localData)
                    }else{
                        Result.Error(result.error)
                    }
                }

                is Result.Success -> {
                    val data = result.data.map { it.toFilm() }
                    localFilmDataSource.upsertFilms(data)
                    Result.Success(data)
                }
            }
        } else {
            Result.Success(localData)
        }
    }
}
