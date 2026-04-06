package com.example.swapi.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<FavoriteEntity>>
    
    @Query("SELECT * FROM favorites WHERE characterId = :id")
    suspend fun isFavorite(id: String): FavoriteEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToFavorites(favorite: FavoriteEntity)
    
    @Delete
    suspend fun removeFromFavorites(favorite: FavoriteEntity)
    
    @Query("DELETE FROM favorites WHERE characterId = :id")
    suspend fun removeById(id: String)
}
