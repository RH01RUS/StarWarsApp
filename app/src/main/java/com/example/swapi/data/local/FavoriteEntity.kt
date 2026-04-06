package com.example.swapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteEntity(
    @PrimaryKey
    val characterId: String,
    val timestamp: Long = System.currentTimeMillis()
)
