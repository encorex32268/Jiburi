package com.lihan.jiburi.film.presentation.detail

import com.lihan.jiburi.film.domain.model.Film

data class DetailState(
    val isLoading: Boolean = false,
    val film: Film? = null
)
