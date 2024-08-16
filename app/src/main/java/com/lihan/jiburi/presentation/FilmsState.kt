package com.lihan.jiburi.presentation

import com.lihan.jiburi.domain.model.Film

data class FilmsState(
    val items: List<Film> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
