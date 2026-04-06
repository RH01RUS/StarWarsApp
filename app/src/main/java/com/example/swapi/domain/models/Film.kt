package com.example.swapi.domain.models

data class Film(
    val id: String,
    val title: String,
    val episodeId: Int,
    val openingCrawl: String,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val url: String
)

data class Species(
    val id: String,
    val name: String,
    val classification: String,
    val designation: String,
    val averageHeight: String,
    val averageLifespan: String,
    val language: String,
    val homeworld: String?,
    val people: List<String>,
    val films: List<String>,
    val url: String
)

data class Starship(
    val id: String,
    val name: String,
    val model: String,
    val starshipClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val maxAtmospheringSpeed: String,
    val hyperdriveRating: String,
    val MGLT: String,
    val cargoCapacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String
)

data class Vehicle(
    val id: String,
    val name: String,
    val model: String,
    val vehicleClass: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val maxAtmospheringSpeed: String,
    val cargoCapacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String
)
