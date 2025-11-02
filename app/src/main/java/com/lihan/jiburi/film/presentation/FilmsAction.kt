package com.lihan.jiburi.film.presentation

sealed interface FilmsAction{
    data object ReloadData: FilmsAction
}