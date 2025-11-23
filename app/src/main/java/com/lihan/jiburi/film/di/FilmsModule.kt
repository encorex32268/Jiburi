package com.lihan.jiburi.film.di

import com.lihan.jiburi.film.data.repository.DetailRepositoryImpl
import com.lihan.jiburi.film.data.repository.FilmsRepositoryImpl
import com.lihan.jiburi.film.domain.repository.DetailRepository
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import com.lihan.jiburi.film.presentation.FilmsViewModel
import com.lihan.jiburi.film.presentation.detail.DetailViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val filmsModule = module {
    singleOf(::FilmsRepositoryImpl).bind<FilmsRepository>()
    viewModel { FilmsViewModel(get()) }
    singleOf(::DetailRepositoryImpl).bind<DetailRepository>()
    viewModel { DetailViewModel(get(),get()) }

}