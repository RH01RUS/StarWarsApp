package com.example.swapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FavoriteEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SwapiDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
