package com.lihan.jiburi.presentation

import com.lihan.jiburi.domain.util.Error

sealed interface FilmsUiEvent {
    data class ApiError(val errorMessage: String): FilmsUiEvent
}