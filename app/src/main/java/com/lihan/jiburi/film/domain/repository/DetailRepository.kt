package com.lihan.jiburi.film.domain.repository

import com.lihan.jiburi.film.domain.model.Film
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    fun getFilmById(id: String): Flow<Film>?
}