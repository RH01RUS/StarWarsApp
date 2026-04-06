package com.example.swapi.presentation.viewmodel

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
class PlanetsViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(PlanetsState())
    val uiState: StateFlow<PlanetsState> = _uiState.asStateFlow()
    
    init {
        loadPlanets()
    }
    
    fun loadPlanets() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val planets = repository.getAllPlanets()
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        planets = planets,
                        allPlanets = planets
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message ?: "Unknown error") 
                }
            }
        }
    }
    
    fun searchPlanets(query: String) {
        viewModelScope.launch {
            if (query.isBlank()) {
                _uiState.update { 
                    it.copy(
                        planets = it.allPlanets,
                        isSearching = false
                    )
                }
                return@launch
            }
            
            _uiState.update { it.copy(isLoading = true, isSearching = true) }
            try {
                val results = repository.searchPlanets(query)
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        planets = results
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
    
    fun refresh() {
        loadPlanets()
    }
}

data class PlanetsState(
    val isLoading: Boolean = false,
    val planets: List<Planet> = emptyList(),
    val allPlanets: List<Planet> = emptyList(),
    val error: String? = null,
    val isSearching: Boolean = false
)
