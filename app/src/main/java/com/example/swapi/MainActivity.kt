package com.example.swapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.swapi.presentation.navigation.Screen
import com.example.swapi.presentation.ui.screens.*
import com.example.swapi.presentation.ui.screens.films.FilmDetailScreen
import com.example.swapi.presentation.ui.screens.films.FilmsScreen
import com.example.swapi.presentation.ui.screens.planets.PlanetDetailScreen
import com.example.swapi.presentation.ui.screens.planets.PlanetsScreen
import com.example.swapi.presentation.ui.screens.species.SpeciesDetailScreen
import com.example.swapi.presentation.ui.screens.species.SpeciesScreen
import com.example.swapi.presentation.ui.screens.starships.StarshipDetailScreen
import com.example.swapi.presentation.ui.screens.starships.StarshipsScreen
import com.example.swapi.presentation.ui.screens.vehicles.VehicleDetailScreen
import com.example.swapi.presentation.ui.screens.vehicles.VehiclesScreen
import com.example.swapi.ui.theme.SwapiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SwapiTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    StarWarsApp()
                }
            }
        }
    }
}

@Composable
fun StarWarsApp() {
    val navController = rememberNavController()
    val items = listOf(
        Screen.Characters,
        Screen.Planets,
        Screen.Films,
        Screen.Starships,
        Screen.Vehicles,
        Screen.Species,
        Screen.Compare
    )
    
    var selectedItem by remember { mutableStateOf(items[0]) }
    
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color(0xFF1A1A2E),
                tonalElevation = 0.dp
            ) {
                items.forEach { screen ->
                    NavigationBarItem(
                        selected = selectedItem == screen,
                        onClick = {
                            selectedItem = screen
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Text(
                                text = screen.icon,
                                fontSize = 24.sp
                            )
                        },
                        label = null,
                        alwaysShowLabel = false,
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFFFFD700),
                            unselectedIconColor = Color(0xFFB0B0B0),
                            indicatorColor = Color(0xFFE94560).copy(alpha = 0.2f)
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Characters.route,
            modifier = Modifier.fillMaxSize()
        ) {
            composable(Screen.Characters.route) {
                CharacterListScreen(
                    onCharacterClick = { characterId ->
                        navController.navigate("character_detail/$characterId")
                    }
                )
            }
            composable(Screen.Planets.route) {
                PlanetsScreen(
                    onPlanetClick = { planetId ->
                        navController.navigate("planet_detail/$planetId")
                    }
                )
            }
            composable(Screen.Films.route) {
                FilmsScreen(
                    onFilmClick = { filmId ->
                        navController.navigate("film_detail/$filmId")
                    }
                )
            }
            composable(Screen.Starships.route) {
                StarshipsScreen(
                    onStarshipClick = { starshipId ->
                        navController.navigate("starship_detail/$starshipId")
                    }
                )
            }
            composable(Screen.Vehicles.route) {
                VehiclesScreen(
                    onVehicleClick = { vehicleId ->
                        navController.navigate("vehicle_detail/$vehicleId")
                    }
                )
            }
            composable(Screen.Species.route) {
                SpeciesScreen(
                    onSpeciesClick = { speciesId ->
                        navController.navigate("species_detail/$speciesId")
                    }
                )
            }
            composable(Screen.Compare.route) {
                CompareCharactersScreen(
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("character_detail/{characterId}") { backStackEntry ->
                val characterId = backStackEntry.arguments?.getString("characterId") ?: ""
                CharacterDetailScreen(
                    characterId = characterId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("planet_detail/{planetId}") { backStackEntry ->
                val planetId = backStackEntry.arguments?.getString("planetId") ?: ""
                PlanetDetailScreen(
                    planetId = planetId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("film_detail/{filmId}") { backStackEntry ->
                val filmId = backStackEntry.arguments?.getString("filmId") ?: ""
                FilmDetailScreen(
                    filmId = filmId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("starship_detail/{starshipId}") { backStackEntry ->
                val starshipId = backStackEntry.arguments?.getString("starshipId") ?: ""
                StarshipDetailScreen(
                    starshipId = starshipId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("vehicle_detail/{vehicleId}") { backStackEntry ->
                val vehicleId = backStackEntry.arguments?.getString("vehicleId") ?: ""
                VehicleDetailScreen(
                    vehicleId = vehicleId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
            composable("species_detail/{speciesId}") { backStackEntry ->
                val speciesId = backStackEntry.arguments?.getString("speciesId") ?: ""
                SpeciesDetailScreen(
                    speciesId = speciesId,
                    onNavigateUp = { navController.navigateUp() }
                )
            }
        }
    }
}
