package com.example.swapi.presentation.viewmodel

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
class StarshipsViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(StarshipsState())
    val uiState: StateFlow<StarshipsState> = _uiState.asStateFlow()
    
    init {
        loadStarships()
    }
    
    fun loadStarships() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val starships = repository.getAllStarships()
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        starships = starships
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
        loadStarships()
    }
}

data class StarshipsState(
    val isLoading: Boolean = false,
    val starships: List<Starship> = emptyList(),
    val error: String? = null
)
