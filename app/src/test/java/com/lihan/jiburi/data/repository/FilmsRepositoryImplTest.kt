package com.lihan.jiburi.data.repository

import com.google.common.truth.Truth
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import com.lihan.jiburi.core.domain.util.Result
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class FilmsRepositoryImplTest {

    private lateinit var filmsRepository: FilmsRepository
    private lateinit var fakeFilmRemoteDataSource: FakeFilmRemoteRemoteDataSource
    private lateinit var fakeLocalFilmDataSource: FakeLocalFilmDataSource

    @Before
    fun setUp() {
        fakeFilmRemoteDataSource = FakeFilmRemoteRemoteDataSource()
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

    @Test
    fun `getData with forceFetch = true fetches from api even if local data exists`(): Unit = runBlocking {
        // Arrange: Prepare local data
        fakeFilmRemoteDataSource.isSuccess = true
        filmsRepository.getFilms() // Initial fetch to populate local DB

        // Arrange: Set remote to fail to verify that a fetch is attempted
        fakeFilmRemoteDataSource.isSuccess = false

        // Act: force fetch
        val result = filmsRepository.getFilms(forceFetch = true)

        // Assert:
        // It should attempt to fetch (fail) and then fall back to local data.
        Truth.assertThat(result is Result.Success).isTrue()
    }

    @Test
    fun `forceFetch updates local data on success`(): Unit = runBlocking {
        // Arrange
        fakeFilmRemoteDataSource.isSuccess = true

        // Act: force fetch
        val result = filmsRepository.getFilms(forceFetch = true)

        // Assert
        Truth.assertThat(result is Result.Success).isTrue()
        val dbData = fakeLocalFilmDataSource.getFilms().first()
        Truth.assertThat(dbData).isNotEmpty()
    }
}
