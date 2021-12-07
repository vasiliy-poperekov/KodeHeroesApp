package com.example.kodeheroesapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kodeheroesapp.data.contracts.HeroContract
import com.example.kodeheroesapp.data.enteties.HeroEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addListHeroEntity(heroesEntityList: List<HeroEntity>)

    @Query("SELECT * FROM ${HeroContract.TABLE_NAME}")
    fun getHeroEntitiesList(): Flow<List<HeroEntity>?>
}