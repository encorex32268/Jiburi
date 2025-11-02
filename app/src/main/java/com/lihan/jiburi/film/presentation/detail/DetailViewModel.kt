package com.lihan.jiburi.film.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lihan.jiburi.film.domain.repository.DetailRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: DetailRepository
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun onAction(action: DetailAction){
        when(action){
            DetailAction.OnBack -> {
                _state.update { it.copy(
                    film = null
                ) }
            }
            is DetailAction.ReloadFilm -> getFilmById(action.id)
        }
    }

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