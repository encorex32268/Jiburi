package com.lihan.jiburi.core.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lihan.jiburi.core.data.local.entity.FilmEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentity")
    fun getFilms(): Flow<List<FilmEntity>>

    @Query("SELECT * FROM filmentity WHERE id=:filmId")
    fun getFilmById(filmId: String): Flow<FilmEntity>?

    @Upsert
    suspend fun upsertFilms(films: List<FilmEntity>)

    @Query("DELETE FROM filmentity")
    suspend fun deleteAllFilms()
}