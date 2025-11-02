package com.lihan.jiburi.film.presentation

sealed interface FilmsUiEvent {
    data class ApiError(val errorMessage: String): FilmsUiEvent
}