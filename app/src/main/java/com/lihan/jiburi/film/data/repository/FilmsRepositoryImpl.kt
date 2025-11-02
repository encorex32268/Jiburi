package com.lihan.jiburi.film.data.repository

import com.lihan.jiburi.core.data.local.LocalFilmDataSource
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSourceImpl
import com.lihan.jiburi.core.domain.util.DataError
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.data.mapper.toFilm
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.first

class FilmsRepositoryImpl(
    private val filmRemoteDataSource: FilmRemoteDataSourceImpl,
    private val localFilmDataSource: LocalFilmDataSource
): FilmsRepository{

    override suspend fun getFilms(): Result<List<Film>, DataError.Network> {
        val localData = localFilmDataSource.getFilms().first()
        //if local db is empty , call api
        return if (localData.isEmpty()){
            val result = filmRemoteDataSource.getFilms()
            when(result){
                is Result.Error -> {
                    Result.Error(result.error)
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