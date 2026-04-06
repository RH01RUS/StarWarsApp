package com.example.swapi.data.repository

import com.example.swapi.data.remote.SwapiService
import com.example.swapi.domain.models.*
import com.example.swapi.domain.repository.SwapiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SwapiRepositoryImpl @Inject constructor(
    private val api: SwapiService
) : SwapiRepository {
    
    override suspend fun getAllCharacters(): List<Character> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllPeople(1)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getCharacterById(id: String): Character? = withContext(Dispatchers.IO) {
        try {
            val response = api.getPerson(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
    
    override suspend fun searchCharacters(query: String): List<Character> = withContext(Dispatchers.IO) {
        try {
            if (query.isBlank()) return@withContext emptyList()
            val response = api.searchPeople(query)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getAllPlanets(): List<Planet> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllPlanets(1)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getPlanet(id: String): Planet? = withContext(Dispatchers.IO) {
        try {
            val response = api.getPlanet(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
    
    override suspend fun searchPlanets(query: String): List<Planet> = withContext(Dispatchers.IO) {
        try {
            if (query.isBlank()) return@withContext emptyList()
            val response = api.searchPlanets(query)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getAllFilms(): List<Film> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllFilms()
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getFilm(id: String): Film? = withContext(Dispatchers.IO) {
        try {
            val response = api.getFilm(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
    
    override suspend fun getAllSpecies(): List<Species> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllSpecies(1)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getSpecies(id: String): Species? = withContext(Dispatchers.IO) {
        try {
            val response = api.getSpecies(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
    
    override suspend fun getAllStarships(): List<Starship> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllStarships(1)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getStarship(id: String): Starship? = withContext(Dispatchers.IO) {
        try {
            val response = api.getStarship(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
    
    override suspend fun getAllVehicles(): List<Vehicle> = withContext(Dispatchers.IO) {
        try {
            val response = api.getAllVehicles(1)
            response.results.map { it.toDomain() }
        } catch (e: Exception) {
            emptyList()
        }
    }
    
    override suspend fun getVehicle(id: String): Vehicle? = withContext(Dispatchers.IO) {
        try {
            val response = api.getVehicle(id.toInt())
            response.toDomain()
        } catch (e: Exception) {
            null
        }
    }
}
