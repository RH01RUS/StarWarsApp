package com.example.swapi.domain.models

data class Planet(
    val id: String,
    val name: String,
    val climate: String,
    val terrain: String,
    val population: String,
    val diameter: String,
    val gravity: String,
    val rotationPeriod: String,
    val orbitalPeriod: String,
    val surfaceWater: String
)
