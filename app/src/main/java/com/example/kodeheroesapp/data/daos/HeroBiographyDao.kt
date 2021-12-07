package com.example.kodeheroesapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kodeheroesapp.data.contracts.HeroBiographyContract
import com.example.kodeheroesapp.data.enteties.HeroBiographyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HeroBiographyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addHeroBiographyEntity(heroesEntityList: HeroBiographyEntity)

    @Query("SELECT * FROM ${HeroBiographyContract.TABLE_NAME} WHERE ${HeroBiographyContract.Columns.ID} = :id")
    fun getHeroEntitiesList(id: Int): Flow<HeroBiographyEntity?>
}