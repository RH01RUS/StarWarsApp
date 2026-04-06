package com.example.swapi.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapi.domain.models.Character
import com.example.swapi.domain.repository.SwapiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CharacterListState())
    val uiState: StateFlow<CharacterListState> = _uiState.asStateFlow()
    
    private val favorites = mutableSetOf<String>()
    
    init {
        loadCharacters()
    }
    
    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            try {
                val characters = repository.getAllCharacters()
                // Восстанавливаем избранное
                val updatedCharacters = characters.map { character ->
                    character.copy(isFavorite = favorites.contains(character.id))
                }
                if (updatedCharacters.isEmpty()) {
                    _uiState.update { it.copy(isLoading = false, isEmpty = true) }
                } else {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            characters = updatedCharacters,
                            allCharacters = updatedCharacters
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message ?: "Unknown error") 
                }
            }
        }
    }
    
    fun toggleFavorite(characterId: String) {
        if (favorites.contains(characterId)) {
            favorites.remove(characterId)
        } else {
            favorites.add(characterId)
        }
        
        // Обновляем состояние
        _uiState.update { state ->
            val updatedCharacters = state.characters.map { character ->
                if (character.id == characterId) {
                    character.copy(isFavorite = favorites.contains(characterId))
                } else {
                    character
                }
            }
            state.copy(characters = updatedCharacters, allCharacters = updatedCharacters)
        }
    }
    
    fun refresh() {
        loadCharacters()
    }
}

data class CharacterListState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val allCharacters: List<Character> = emptyList(),
    val error: String? = null,
    val isEmpty: Boolean = false,
    val isSearching: Boolean = false
)
