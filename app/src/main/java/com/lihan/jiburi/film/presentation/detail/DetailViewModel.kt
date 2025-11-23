package com.lihan.jiburi.film.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.lihan.jiburi.core.presentation.navigation.FilmDetailRoute
import com.lihan.jiburi.film.domain.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DetailRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var routeData = savedStateHandle.toRoute<FilmDetailRoute>()

    private var isInitFinished = false

    private val _state = MutableStateFlow(DetailState())
    val state = _state
        .onStart {
            if (!isInitFinished){
                getFilmById(routeData.id)
                isInitFinished = true
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            DetailState()
        )

    private fun getFilmById(id: String){
        viewModelScope.launch {
            val film = repository.getFilmById(id)?.first()
            if (film == null) return@launch
            _state.update { it.copy(
                film = film
            ) }
        }
    }

}