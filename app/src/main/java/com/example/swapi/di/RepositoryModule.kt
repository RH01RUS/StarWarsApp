package com.example.swapi.di

import com.example.swapi.data.repository.SwapiRepositoryImpl
import com.example.swapi.domain.repository.SwapiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    
    @Binds
    @Singleton
    abstract fun bindSwapiRepository(
        swapiRepositoryImpl: SwapiRepositoryImpl
    ): SwapiRepository
}
