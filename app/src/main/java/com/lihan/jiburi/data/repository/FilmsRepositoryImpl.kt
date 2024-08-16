package com.lihan.jiburi.data.repository

import android.util.Log
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.repository.FilmsRepository
import com.lihan.jiburi.domain.repository.LocalFilmDataSource
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first

class FilmsRepositoryImpl(
    private val filmRemoteDataSource: FilmRemoteDataSource,
    private val localFilmDataSource: LocalFilmDataSource
): FilmsRepository {
    override suspend fun getFilms(): Result<List<Film>, DataError.Network> {
        return when(val result = filmRemoteDataSource.getFilms()){

            is Result.Error -> {
                val localData = localFilmDataSource.getFilms().first()
                if (localData.isEmpty()){
                    Result.Error(result.error)
                }else{
                    Result.Success(localData)
                }
            }

            is Result.Success -> {
                localFilmDataSource.upsertFilms(result.data)
                Result.Success(result.data)
            }
        }
    }

}