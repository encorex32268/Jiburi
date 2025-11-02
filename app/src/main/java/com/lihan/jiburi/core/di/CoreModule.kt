package com.lihan.jiburi.core.di

import androidx.room.Room
import com.lihan.jiburi.core.data.local.JiburiRoomDatabase
import com.lihan.jiburi.core.data.local.LocalFilmDataSource
import com.lihan.jiburi.core.data.local.LocalFilmDataSourceImpl
import com.lihan.jiburi.core.data.network.HttpClientFactory
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSource
import com.lihan.jiburi.core.data.remote.FilmRemoteDataSourceImpl
import io.ktor.client.HttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreModule = module {
    single{
        HttpClientFactory().build()
    }.bind<HttpClient>()

    single{
        Room.databaseBuilder(
            androidContext(),
            JiburiRoomDatabase::class.java,
            "jiburi.db"
        ).build()
    }
    single { get<JiburiRoomDatabase>().filmDao }
    singleOf(::FilmRemoteDataSourceImpl).bind<FilmRemoteDataSource>()
    singleOf(::LocalFilmDataSourceImpl).bind<LocalFilmDataSource>()

}