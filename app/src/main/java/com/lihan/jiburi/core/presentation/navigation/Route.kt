package com.lihan.jiburi.core.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data class FilmDetailRoute(val id: String)

@Serializable
data object FilmListRoute