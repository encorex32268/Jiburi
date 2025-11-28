package com.lihan.jiburi.film.data.repository

import com.lihan.jiburi.core.data.local.LocalFilmDataSource
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSource
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.data.mapper.toFilm
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.first

class FilmsRepositoryImpl(
    private val filmRemoteDataSource: FilmRemoteDataSource,
    private val localFilmDataSource: LocalFilmDataSource
): FilmsRepository{

    override suspend fun getFilms(forceFetch: Boolean): Result<List<Film>, DataError.Network> {
        val localData = localFilmDataSource.getFilms().first()
        val shouldFetch = localData.isEmpty() || forceFetch

        return if (shouldFetch){
            val result = filmRemoteDataSource.getFilms()
            when(result){
                is Result.Error -> {
                    if (localData.isNotEmpty()){
                        Result.Success(localData)
                    }else{
                        Result.Error(result.error)
                    }
                }
                is Result.Success -> {
                    val films = result.data.map { it.toFilm() }
                    localFilmDataSource.upsertFilms(films = films)
                    Result.Success(films)
                }
            }
        }else{
            Result.Success(localData)
        }

    }

}
