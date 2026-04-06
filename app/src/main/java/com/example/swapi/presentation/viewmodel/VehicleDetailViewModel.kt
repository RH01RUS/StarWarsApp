package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
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
class VehicleDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val vehicleId: String = savedStateHandle["vehicleId"] ?: ""
    
    private val _uiState = MutableStateFlow(VehicleDetailState())
    val uiState: StateFlow<VehicleDetailState> = _uiState.asStateFlow()
    
    init {
        loadVehicleDetails()
    }
    
    private fun loadVehicleDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val vehicle = repository.getVehicle(vehicleId)
                _uiState.update { 
                    it.copy(isLoading = false, vehicle = vehicle)
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class VehicleDetailState(
    val isLoading: Boolean = false,
    val vehicle: Vehicle? = null,
    val error: String? = null
)
