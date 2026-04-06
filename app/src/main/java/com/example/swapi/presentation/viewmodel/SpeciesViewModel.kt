package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Species
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpeciesViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SpeciesState())
    val uiState: StateFlow<SpeciesState> = _uiState.asStateFlow()
    
    init {
        loadSpecies()
    }
    
    fun loadSpecies() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val speciesList = repository.getAllSpecies()
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        species = speciesList
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
        loadSpecies()
    }
}

data class SpeciesState(
    val isLoading: Boolean = false,
    val species: List<Species> = emptyList(),
    val error: String? = null
)
