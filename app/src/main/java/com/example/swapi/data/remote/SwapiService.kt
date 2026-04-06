package com.example.swapi.data.remote

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SwapiService {
    // People
    @GET("people/")
    suspend fun getAllPeople(@Query("page") page: Int = 1): PeopleResponse
    
    @GET("people/")
    suspend fun searchPeople(@Query("search") query: String): PeopleResponse
    
    @GET("people/{id}/")
    suspend fun getPerson(@Path("id") id: Int): PersonResponse
    
    // Planets
    @GET("planets/")
    suspend fun getAllPlanets(@Query("page") page: Int = 1): PlanetsResponse
    
    @GET("planets/{id}/")
    suspend fun getPlanet(@Path("id") id: Int): PlanetResponse
    
    @GET("planets/")
    suspend fun searchPlanets(@Query("search") query: String): PlanetsResponse
    
    // Films
    @GET("films/")
    suspend fun getAllFilms(): FilmsResponse
    
    @GET("films/{id}/")
    suspend fun getFilm(@Path("id") id: Int): FilmResponse
    
    // Species
    @GET("species/")
    suspend fun getAllSpecies(@Query("page") page: Int = 1): SpeciesListResponse
    
    @GET("species/{id}/")
    suspend fun getSpecies(@Path("id") id: Int): SpeciesResponse
    
    @GET("species/")
    suspend fun searchSpecies(@Query("search") query: String): SpeciesListResponse
    
    // Starships
    @GET("starships/")
    suspend fun getAllStarships(@Query("page") page: Int = 1): StarshipsListResponse
    
    @GET("starships/{id}/")
    suspend fun getStarship(@Path("id") id: Int): StarshipResponse
    
    @GET("starships/")
    suspend fun searchStarships(@Query("search") query: String): StarshipsListResponse
    
    // Vehicles
    @GET("vehicles/")
    suspend fun getAllVehicles(@Query("page") page: Int = 1): VehiclesListResponse
    
    @GET("vehicles/{id}/")
    suspend fun getVehicle(@Path("id") id: Int): VehicleResponse
    
    @GET("vehicles/")
    suspend fun searchVehicles(@Query("search") query: String): VehiclesListResponse
}

data class PeopleResponse(
    val count: Int, 
    val next: String?, 
    val previous: String?, 
    val results: List<PersonResponse>
)

data class PlanetsResponse(
    val count: Int, 
    val next: String?, 
    val previous: String?, 
    val results: List<PlanetResponse>
)

data class FilmsResponse(
    val count: Int, 
    val results: List<FilmResponse>
)

data class SpeciesListResponse(
    val count: Int, 
    val next: String?, 
    val previous: String?, 
    val results: List<SpeciesResponse>
)

data class StarshipsListResponse(
    val count: Int, 
    val next: String?, 
    val previous: String?, 
    val results: List<StarshipResponse>
)

data class VehiclesListResponse(
    val count: Int, 
    val next: String?, 
    val previous: String?, 
    val results: List<VehicleResponse>
)

data class PersonResponse(
    val name: String,
    val height: String,
    val mass: String,
    val hair_color: String,
    val skin_color: String,
    val eye_color: String,
    val birth_year: String,
    val gender: String,
    val homeworld: String,
    val films: List<String>,
    val species: List<String>,
    val vehicles: List<String>,
    val starships: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Character {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Character(
            id = id,
            name = name,
            height = height,
            mass = mass,
            hairColor = hair_color,
            skinColor = skin_color,
            eyeColor = eye_color,
            birthYear = birth_year,
            gender = gender,
            homeworld = homeworld,
            films = films,
            species = species,
            vehicles = vehicles,
            starships = starships
        )
    }
}

data class PlanetResponse(
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val diameter: String,
    val gravity: String,
    val rotation_period: String,
    val orbital_period: String,
    val surface_water: String,
    val residents: List<String>,
    val films: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Planet {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Planet(
            id = id,
            name = name,
            climate = climate,
            terrain = terrain,
            population = population,
            diameter = diameter,
            gravity = gravity,
            rotationPeriod = rotation_period,
            orbitalPeriod = orbital_period,
            surfaceWater = surface_water
        )
    }
}

data class FilmResponse(
    val title: String,
    val episode_id: Int,
    val opening_crawl: String,
    val director: String,
    val producer: String,
    val release_date: String,
    val characters: List<String>,
    val planets: List<String>,
    val starships: List<String>,
    val vehicles: List<String>,
    val species: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Film {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Film(
            id = id,
            title = title,
            episodeId = episode_id,
            openingCrawl = opening_crawl,
            director = director,
            producer = producer,
            releaseDate = release_date,
            characters = characters,
            planets = planets,
            starships = starships,
            vehicles = vehicles,
            species = species,
            url = url
        )
    }
}

data class SpeciesResponse(
    val name: String,
    val classification: String,
    val designation: String,
    val average_height: String,
    val average_lifespan: String,
    val language: String,
    val homeworld: String?,
    val people: List<String>,
    val films: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Species {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Species(
            id = id,
            name = name,
            classification = classification,
            designation = designation,
            averageHeight = average_height,
            averageLifespan = average_lifespan,
            language = language,
            homeworld = homeworld,
            people = people,
            films = films,
            url = url
        )
    }
}

data class StarshipResponse(
    val name: String,
    val model: String,
    val starship_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val hyperdrive_rating: String,
    val MGLT: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Starship {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Starship(
            id = id,
            name = name,
            model = model,
            starshipClass = starship_class,
            manufacturer = manufacturer,
            costInCredits = cost_in_credits,
            length = length,
            crew = crew,
            passengers = passengers,
            maxAtmospheringSpeed = max_atmosphering_speed,
            hyperdriveRating = hyperdrive_rating,
            MGLT = MGLT,
            cargoCapacity = cargo_capacity,
            consumables = consumables,
            films = films,
            pilots = pilots,
            url = url
        )
    }
}

data class VehicleResponse(
    val name: String,
    val model: String,
    val vehicle_class: String,
    val manufacturer: String,
    val cost_in_credits: String,
    val length: String,
    val crew: String,
    val passengers: String,
    val max_atmosphering_speed: String,
    val cargo_capacity: String,
    val consumables: String,
    val films: List<String>,
    val pilots: List<String>,
    val url: String
) {
    fun toDomain(): com.example.swapi.domain.models.Vehicle {
        val id = url.split("/").filter { it.isNotEmpty() }.last()
        return com.example.swapi.domain.models.Vehicle(
            id = id,
            name = name,
            model = model,
            vehicleClass = vehicle_class,
            manufacturer = manufacturer,
            costInCredits = cost_in_credits,
            length = length,
            crew = crew,
            passengers = passengers,
            maxAtmospheringSpeed = max_atmosphering_speed,
            cargoCapacity = cargo_capacity,
            consumables = consumables,
            films = films,
            pilots = pilots,
            url = url
        )
    }
}
