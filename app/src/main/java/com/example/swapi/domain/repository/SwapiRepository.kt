package com.example.swapi.domain.repository

import com.example.swapi.domain.models.*

interface SwapiRepository {
    // Characters
    suspend fun getAllCharacters(): List<Character>
    suspend fun getCharacterById(id: String): Character?
    suspend fun searchCharacters(query: String): List<Character>
    
    // Planets
    suspend fun getAllPlanets(): List<Planet>
    suspend fun getPlanet(id: String): Planet?
    suspend fun searchPlanets(query: String): List<Planet>
    
    // Films
    suspend fun getAllFilms(): List<Film>
    suspend fun getFilm(id: String): Film?
    
    // Species
    suspend fun getAllSpecies(): List<Species>
    suspend fun getSpecies(id: String): Species?
    
    // Starships
    suspend fun getAllStarships(): List<Starship>
    suspend fun getStarship(id: String): Starship?
    
    // Vehicles
    suspend fun getAllVehicles(): List<Vehicle>
    suspend fun getVehicle(id: String): Vehicle?
}
