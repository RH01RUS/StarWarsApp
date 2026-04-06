package com.example.swapi.presentation.ui.screens.vehicles

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.swapi.presentation.viewmodel.VehicleDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VehicleDetailScreen(
    vehicleId: String,
    onNavigateUp: () -> Unit,
    viewModel: VehicleDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vehicle Details", color = Color(0xFFFFD700)) },
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
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(color = Color(0xFFE94560))
                }
                state.error != null -> {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Error: ${state.error}", color = Color.White)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { onNavigateUp() }) {
                            Text("Go Back")
                        }
                    }
                }
                state.vehicle != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        item {
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1A2E))
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = state.vehicle!!.name,
                                        style = MaterialTheme.typography.headlineMedium,
                                        color = Color(0xFFFFD700)
                                    )
                                    Divider(color = Color(0xFFE94560), modifier = Modifier.padding(vertical = 8.dp))
                                    DetailRowVehicle("Model", state.vehicle!!.model)
                                    DetailRowVehicle("Class", state.vehicle!!.vehicleClass)
                                    DetailRowVehicle("Manufacturer", state.vehicle!!.manufacturer)
                                    DetailRowVehicle("Length", "${state.vehicle!!.length} m")
                                    DetailRowVehicle("Crew", state.vehicle!!.crew)
                                    DetailRowVehicle("Passengers", state.vehicle!!.passengers)
                                    DetailRowVehicle("Cargo Capacity", "${state.vehicle!!.cargoCapacity} kg")
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetailRowVehicle(label: String, value: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFB0B0B0)
        )
        Text(
            text = value.ifEmpty { "unknown" },
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
    }
}
