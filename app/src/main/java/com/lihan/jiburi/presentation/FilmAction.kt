package com.lihan.jiburi.presentation

sealed interface FilmAction{
    data object GetData: FilmAction
}