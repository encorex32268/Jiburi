package com.lihan.jiburi.data.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.lihan.jiburi.data.local.entity.FilmEntity
import com.lihan.jiburi.domain.model.Film
import com.lihan.jiburi.domain.util.DataError
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {
    @Query("SELECT * FROM filmentity")
    fun getFilms(): Flow<List<FilmEntity>>

    @Upsert
    suspend fun upsertFilms(films: List<FilmEntity>)

    @Query("DELETE FROM filmentity")
    suspend fun deleteAllFilms()
}