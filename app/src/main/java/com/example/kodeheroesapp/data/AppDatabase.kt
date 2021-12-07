package com.example.kodeheroesapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kodeheroesapp.data.AppDatabase.Companion.DB_VERSION
import com.example.kodeheroesapp.data.daos.HeroBiographyDao
import com.example.kodeheroesapp.data.daos.HeroDao
import com.example.kodeheroesapp.data.enteties.HeroBiographyEntity
import com.example.kodeheroesapp.data.enteties.HeroEntity

@Database(
    entities = [
        HeroEntity::class,
        HeroBiographyEntity::class
    ], version = DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getHeroDao(): HeroDao
    abstract fun getHeroBiographyDao(): HeroBiographyDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "database"
    }
}