package com.example.swapi.presentation.navigation

sealed class Screen(val route: String, val title: String, val icon: String) {
    object Characters : Screen("characters", "Characters", "👤")
    object Planets : Screen("planets", "Planets", "🌍")
    object Films : Screen("films", "Films", "🎬")
    object Starships : Screen("starships", "Starships", "🚀")
    object Vehicles : Screen("vehicles", "Vehicles", "🚗")
    object Species : Screen("species", "Species", "👾")
    object Compare : Screen("compare", "Compare", "⚔️")
}
