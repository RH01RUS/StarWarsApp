package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Starship
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarshipDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val starshipId: String = savedStateHandle["starshipId"] ?: ""
    
    private val _uiState = MutableStateFlow(StarshipDetailState())
    val uiState: StateFlow<StarshipDetailState> = _uiState.asStateFlow()
    
    init {
        loadStarshipDetails()
    }
    
    private fun loadStarshipDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val starship = repository.getStarship(starshipId)
                _uiState.update { 
                    it.copy(isLoading = false, starship = starship)
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class StarshipDetailState(
    val isLoading: Boolean = false,
    val starship: Starship? = null,
    val error: String? = null
)
