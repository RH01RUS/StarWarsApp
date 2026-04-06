package com.example.swapi.presentation.viewmodel

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
class FilmsViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(FilmsState())
    val uiState: StateFlow<FilmsState> = _uiState.asStateFlow()
    
    init {
        loadFilms()
    }
    
    fun loadFilms() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val films = repository.getAllFilms()
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        films = films
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message ?: "Unknown error") 
                }
            }
        }
    }
    
    fun refresh() {
        loadFilms()
    }
}

data class FilmsState(
    val isLoading: Boolean = false,
    val films: List<Film> = emptyList(),
    val error: String? = null
)
