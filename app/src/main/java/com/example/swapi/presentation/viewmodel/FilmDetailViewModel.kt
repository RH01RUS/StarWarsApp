package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Film
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val filmId: String = savedStateHandle["filmId"] ?: ""
    
    private val _uiState = MutableStateFlow(FilmDetailState())
    val uiState: StateFlow<FilmDetailState> = _uiState.asStateFlow()
    
    init {
        loadFilmDetails()
    }
    
    private fun loadFilmDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val film = repository.getFilm(filmId)
                _uiState.update { 
                    it.copy(isLoading = false, film = film)
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class FilmDetailState(
    val isLoading: Boolean = false,
    val film: Film? = null,
    val error: String? = null
)
