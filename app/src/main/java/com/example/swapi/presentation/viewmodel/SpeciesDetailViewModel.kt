package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
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
class SpeciesDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val speciesId: String = savedStateHandle["speciesId"] ?: ""
    
    private val _uiState = MutableStateFlow(SpeciesDetailState())
    val uiState: StateFlow<SpeciesDetailState> = _uiState.asStateFlow()
    
    init {
        loadSpeciesDetails()
    }
    
    private fun loadSpeciesDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val species = repository.getSpecies(speciesId)
                _uiState.update { 
                    it.copy(isLoading = false, species = species)
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class SpeciesDetailState(
    val isLoading: Boolean = false,
    val species: Species? = null,
    val error: String? = null
)
