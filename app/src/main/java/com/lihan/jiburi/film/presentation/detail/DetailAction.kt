package com.lihan.jiburi.film.presentation.detail

sealed interface DetailAction{
    data object OnBack: DetailAction
    data class ReloadFilm(val id: String): DetailAction
}