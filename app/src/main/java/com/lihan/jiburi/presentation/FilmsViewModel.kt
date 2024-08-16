package com.lihan.jiburi.presentation

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.jiburi.domain.repository.FilmDataSource
import com.lihan.jiburi.domain.repository.FilmsRepository
import com.lihan.jiburi.domain.util.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FilmsViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val filmsRepository: FilmsRepository
): ViewModel() {

    private val _state = MutableStateFlow(FilmsState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<FilmsUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    isLoading = true
                )
            }
            when(val result = filmsRepository.getFilms()){
                is Result.Error ->{
                    _state.update {
                        it.copy(
                            errorMessage = result.error.name,
                            isLoading = false
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            items = result.data,
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}