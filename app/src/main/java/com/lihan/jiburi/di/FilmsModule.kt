package com.lihan.jiburi.di

import androidx.room.Room
import com.lihan.jiburi.data.local.JiburiRoomDatabase
import com.lihan.jiburi.data.local.dao.FilmDao
import com.lihan.jiburi.data.repository.FilmRemoteDataSource
import com.lihan.jiburi.data.repository.FilmsRepositoryImpl
import com.lihan.jiburi.data.repository.LocalFilmDataSourceImpl
import com.lihan.jiburi.domain.repository.FilmDataSource
import com.lihan.jiburi.domain.repository.FilmsRepository
import com.lihan.jiburi.domain.repository.LocalFilmDataSource
import com.lihan.jiburi.presentation.FilmsViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val filmsModule = module {
    single{
        Room.databaseBuilder(
            androidApplication(),
            JiburiRoomDatabase::class.java,
            "jiburi.db"
        ).build()
    }
    single { get<JiburiRoomDatabase>().filmDao }
    singleOf(::FilmRemoteDataSource).bind<FilmDataSource>()
    singleOf(::LocalFilmDataSourceImpl).bind<LocalFilmDataSource>()
    singleOf(::FilmsRepositoryImpl).bind<FilmsRepository>()
    viewModelOf(::FilmsViewModel)
}