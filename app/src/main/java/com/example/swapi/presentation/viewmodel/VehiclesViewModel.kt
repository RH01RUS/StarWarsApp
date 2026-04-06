package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Vehicle
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehiclesViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(VehiclesState())
    val uiState: StateFlow<VehiclesState> = _uiState.asStateFlow()
    
    init {
        loadVehicles()
    }
    
    fun loadVehicles() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val vehicles = repository.getAllVehicles()
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        vehicles = vehicles
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
        loadVehicles()
    }
}

data class VehiclesState(
    val isLoading: Boolean = false,
    val vehicles: List<Vehicle> = emptyList(),
    val error: String? = null
)
