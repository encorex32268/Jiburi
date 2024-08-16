package com.lihan.jiburi.core.di

import com.lihan.jiburi.data.network.HttpClientFactory
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single{
        HttpClientFactory().build()
    }.bind<HttpClient>()
}