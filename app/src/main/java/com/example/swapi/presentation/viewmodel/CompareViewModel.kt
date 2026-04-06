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
class CompareViewModel @Inject constructor(
    private val repository: SwapiRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(CompareState())
    val uiState: StateFlow<CompareState> = _uiState.asStateFlow()
    
    init {
        loadFavoriteCharacters()
    }
    
    fun loadFavoriteCharacters() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            try {
                // Здесь нужно получить ID избранных персонажей
                // Для теста пока загружаем первых двух
                val character1 = repository.getCharacterById("1")
                val character2 = repository.getCharacterById("2")
                val characters = listOfNotNull(character1, character2)
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        characters = characters
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(isLoading = false, error = e.message) 
                }
            }
        }
    }
}

data class CompareState(
    val isLoading: Boolean = false,
    val characters: List<Character> = emptyList(),
    val error: String? = null
)
