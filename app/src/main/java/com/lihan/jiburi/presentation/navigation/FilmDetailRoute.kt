package com.lihan.jiburi.presentation.navigation

import com.lihan.jiburi.domain.model.Film
import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailRoute(
    val film: Film
)
