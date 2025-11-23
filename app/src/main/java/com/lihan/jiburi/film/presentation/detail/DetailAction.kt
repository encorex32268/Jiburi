package com.lihan.jiburi.film.presentation.detail

sealed interface DetailAction{
    data object OnBack: DetailAction
}