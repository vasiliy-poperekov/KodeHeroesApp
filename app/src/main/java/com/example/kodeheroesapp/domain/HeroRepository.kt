package com.example.kodeheroesapp.domain

import com.example.kodeheroesapp.domain.enteties.Hero
import com.example.kodeheroesapp.domain.enteties.HeroBiography
import kotlinx.coroutines.flow.Flow

interface HeroRepository {
    fun getList(): Flow<List<Hero>?>

    suspend fun refreshList()

    fun getBiography(id: Int): Flow<HeroBiography?>

    suspend fun refreshBiography(id: Int)
}