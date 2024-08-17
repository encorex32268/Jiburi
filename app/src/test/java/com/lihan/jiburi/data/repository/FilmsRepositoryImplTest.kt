package com.lihan.jiburi.data.repository

import com.google.common.truth.Truth
import com.lihan.jiburi.domain.repository.FilmsRepository
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilmsRepositoryImplTest {

    private lateinit var filmsRepository: FilmsRepository
    private lateinit var fakeFilmRemoteDataSource: FakeFilmRemoteDataSource
    private lateinit var fakeLocalFilmDataSource: FakeLocalFilmDataSource

    @Before
    fun setUp() {
        fakeFilmRemoteDataSource = FakeFilmRemoteDataSource()
        fakeLocalFilmDataSource = FakeLocalFilmDataSource()
        filmsRepository = FakeFilmsRepositoryImpl(
            filmRemoteDataSource = fakeFilmRemoteDataSource,
            localFilmDataSource = fakeLocalFilmDataSource
        )
    }

    @Test
    fun `getData from api when success`(): Unit = runBlocking{
        fakeFilmRemoteDataSource.isSuccess = true
        val result = filmsRepository.getFilms()
        val data = when(result){
            is Result.Error   -> {
                emptyList()
            }
            is Result.Success -> {
                result.data
            }
        }

        val dbData = fakeLocalFilmDataSource.getFilms().first()
        Truth.assertThat(dbData.isNotEmpty()).isTrue()
        Truth.assertThat(result is Result.Success).isTrue()
        Truth.assertThat(data.isNotEmpty())
    }

    @Test
    fun `getData from api when error`(): Unit = runBlocking{
        fakeFilmRemoteDataSource.isSuccess = false
        val result = filmsRepository.getFilms()
        val data = when(result){
            is Result.Error   -> {
                emptyList()
            }
            is Result.Success -> {
                result.data
            }
        }
        val dbData = fakeLocalFilmDataSource.getFilms().first()
        Truth.assertThat(dbData.isEmpty()).isTrue()

        Truth.assertThat(result is Result.Error).isTrue()
        Truth.assertThat(data.isEmpty())
    }


}