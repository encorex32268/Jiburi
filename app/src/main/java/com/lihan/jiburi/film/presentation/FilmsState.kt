package com.lihan.jiburi.film.presentation

import com.lihan.jiburi.film.domain.model.Film

data class FilmsState(
    val items: List<Film> = emptyList(),
    val isLoading: Boolean = false
)
