package com.lihan.jiburi.film.data.repository

import com.lihan.jiburi.core.data.local.LocalFilmDataSource
import com.lihan.jiburi.film.domain.model.Film
import com.lihan.jiburi.film.domain.repository.DetailRepository
import kotlinx.coroutines.flow.Flow

class DetailRepositoryImpl(
    private val localFilmDataSource: LocalFilmDataSource
): DetailRepository{
    override fun getFilmById(id: String): Flow<Film>? {
       return localFilmDataSource.getFilmById(id)
    }
}