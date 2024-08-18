@file:OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)

package com.lihan.jiburi.presentation

import com.google.common.truth.Truth
import com.lihan.jiburi.data.repository.FakeFilmRemoteDataSource
import com.lihan.jiburi.data.repository.FakeFilmsRepositoryImpl
import com.lihan.jiburi.data.repository.FakeLocalFilmDataSource
import com.lihan.jiburi.domain.mapper.toFilm
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.internal.wait

import org.junit.Before
import org.junit.Test

class FilmsViewModelTest {

    private lateinit var viewModel: FilmsViewModel
    private lateinit var fakeFilmsRepositoryImpl: FakeFilmsRepositoryImpl
    private lateinit var fakeFilmRemoteDataSource: FakeFilmRemoteDataSource
    private lateinit var fakeLocalFilmDataSource: FakeLocalFilmDataSource

    @Before
    fun setUp(){
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun `init get data`() = runTest{
        fakeFilmRemoteDataSource = FakeFilmRemoteDataSource().apply {
            isSuccess = true
        }
        fakeLocalFilmDataSource = FakeLocalFilmDataSource()

        fakeFilmsRepositoryImpl = FakeFilmsRepositoryImpl(
            filmRemoteDataSource = fakeFilmRemoteDataSource,
            localFilmDataSource = fakeLocalFilmDataSource
        )
        viewModel = FilmsViewModel(fakeFilmsRepositoryImpl)
        advanceUntilIdle()
        val state = viewModel.state.value
        val dumpData = fakeFilmRemoteDataSource.getFilmsDto().map { it.toFilm() }
        Truth.assertThat(state.items.size == dumpData.size).isTrue()
        Truth.assertThat(state.items[2].title == dumpData[2].title).isTrue()

    }

}