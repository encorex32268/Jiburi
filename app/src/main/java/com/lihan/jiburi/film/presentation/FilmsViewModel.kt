package com.lihan.jiburi.film.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.jiburi.core.domain.util.Result
import com.lihan.jiburi.film.domain.repository.FilmsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val filmsRepository: FilmsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(FilmsState())
    val state = _state.onStart {
        getData()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        FilmsState()
    )

    private val _uiEvent = Channel<FilmsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onAction(action: FilmsAction) {
        when (action) {
            FilmsAction.ReloadData -> getData()
        }
    }

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            when (val result = filmsRepository.getFilms()) {
                is Result.Error -> {
                    _uiEvent.send(
                        FilmsUiEvent.ApiError(result.error.name)
                    )
                }

                is Result.Success -> {
                    _state.update {
                        it.copy(
                            items = result.data
                        )
                    }
                }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}