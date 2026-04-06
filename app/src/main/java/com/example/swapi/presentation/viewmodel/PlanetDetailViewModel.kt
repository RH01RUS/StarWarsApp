package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Planet
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlanetDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val planetId: String = savedStateHandle["planetId"] ?: ""
    
    private val _uiState = MutableStateFlow(PlanetDetailState())
    val uiState: StateFlow<PlanetDetailState> = _uiState.asStateFlow()
    
    init {
        loadPlanetDetails()
    }
    
    private fun loadPlanetDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val planet = repository.getPlanet(planetId)
                _uiState.update { 
                    it.copy(isLoading = false, planet = planet)
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class PlanetDetailState(
    val isLoading: Boolean = false,
    val planet: Planet? = null,
    val error: String? = null
)
