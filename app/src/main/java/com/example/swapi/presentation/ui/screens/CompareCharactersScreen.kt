package com.example.swapi.presentation.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swapi.domain.models.Character
import com.example.swapi.presentation.viewmodel.CompareViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompareCharactersScreen(
    onNavigateUp: () -> Unit,
    viewModel: CompareViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Compare Characters", color = Color(0xFFFFD700)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color(0xFFFFD700))
                    }
                },
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
                state.characters.size < 2 -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Select at least 2 characters to compare",
                            color = Color(0xFFFFD700),
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Tap ❤️ on characters to add to favorites,\nthen come back here",
                            color = Color(0xFFB0B0B0),
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = onNavigateUp) {
                            Text("Go Back")
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Text(
                                text = "Character Comparison",
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color(0xFFFFD700)
                            )
                        }
                        
                        item {
                            CompareTable(characters = state.characters)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CompareTable(characters: List<Character>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with names
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text("Attribute", color = Color(0xFFFFD700), fontSize = 16.sp, modifier = Modifier.weight(1f))
                characters.forEach { character ->
                    Text(
                        text = character.name.take(10),
                        color = Color(0xFFFFD700),
                        fontSize = 14.sp,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            
            Divider(color = Color(0xFFE94560), modifier = Modifier.padding(vertical = 8.dp))
            
            // Height row
            CompareRow("Height", characters.map { "${it.height}cm" })
            
            // Mass row
            CompareRow("Mass", characters.map { "${it.mass}kg" })
            
            // Gender row
            CompareRow("Gender", characters.map { it.gender })
            
            // Hair Color row
            CompareRow("Hair Color", characters.map { it.hairColor })
            
            // Eye Color row
            CompareRow("Eye Color", characters.map { it.eyeColor })
            
            // Birth Year row
            CompareRow("Birth Year", characters.map { it.birthYear })
            
            // Films count row
            CompareRow("Films", characters.map { it.films.size.toString() })
        }
    }
}

@Composable
fun CompareRow(attribute: String, values: List<String>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = attribute,
            color = Color(0xFFB0B0B0),
            fontSize = 14.sp,
            modifier = Modifier.weight(1f)
        )
        values.forEach { value ->
            Text(
                text = value,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
