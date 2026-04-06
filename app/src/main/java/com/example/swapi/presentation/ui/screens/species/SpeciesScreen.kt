package com.example.swapi.presentation.ui.screens.species

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swapi.presentation.ui.components.StarWarsCard
import com.example.swapi.presentation.viewmodel.SpeciesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpeciesScreen(
    onSpeciesClick: (String) -> Unit,
    viewModel: SpeciesViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Species", color = Color(0xFFFFD700)) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1A1A2E))
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color(0xFFE94560)
                    )
                }
                state.error != null -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error: ${state.error}", color = Color.White)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.refresh() }) {
                            Text("Retry")
                        }
                    }
                }
                state.species.isEmpty() -> {
                    Text(
                        text = "No species found",
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.species) { species ->
                            StarWarsCard(onClick = { onSpeciesClick(species.id) }) {
                                Text(
                                    text = species.name,
                                    color = Color(0xFFFFD700),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Classification: ${species.classification}",
                                    color = Color(0xFFB0B0B0),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                                Text(
                                    text = "Language: ${species.language}",
                                    color = Color(0xFFB0B0B0),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
