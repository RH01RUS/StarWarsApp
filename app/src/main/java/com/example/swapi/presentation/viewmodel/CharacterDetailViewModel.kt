package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Character
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
class CharacterDetailViewModel @Inject constructor(
    private val repository: SwapiRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    
    private val characterId: String = savedStateHandle["characterId"] ?: ""
    
    private val _uiState = MutableStateFlow(CharacterDetailState())
    val uiState: StateFlow<CharacterDetailState> = _uiState.asStateFlow()
    
    init {
        loadCharacterDetails()
    }
    
    private fun loadCharacterDetails() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                val character = repository.getCharacterById(characterId)
                if (character != null) {
                    val planetId = extractPlanetId(character.homeworld)
                    val planet = planetId?.let { repository.getPlanet(it) }
                    _uiState.update { 
                        it.copy(isLoading = false, character = character, homeworld = planet)
                    }
                } else {
                    _uiState.update { it.copy(isLoading = false, error = "Character not found") }
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
    
    private fun extractPlanetId(url: String): String? {
        return url.split("/").filter { it.isNotEmpty() }.lastOrNull()
    }
}

data class CharacterDetailState(
    val isLoading: Boolean = false,
    val character: Character? = null,
    val homeworld: Planet? = null,
    val error: String? = null
)
