package com.example.kodeheroesapp.data.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.kodeheroesapp.data.contracts.HeroBiographyContract
import com.example.kodeheroesapp.data.converters.AliasesConverter
import com.example.kodeheroesapp.data.converters.HeroConverter
import com.example.kodeheroesapp.domain.enteties.Hero

@Entity(tableName = HeroBiographyContract.TABLE_NAME)
@TypeConverters(AliasesConverter::class, HeroConverter::class)
data class HeroBiographyEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = HeroBiographyContract.Columns.ID)
    val id: Int,

    @ColumnInfo(name = HeroBiographyContract.Columns.HERO)
    val hero: Hero,

    @ColumnInfo(name = HeroBiographyContract.Columns.ALIASES)
    val aliases: List<String>,

    @ColumnInfo(name = HeroBiographyContract.Columns.PLACE_OF_BIRTH)
    val placeOfBirth: String,

    @ColumnInfo(name = HeroBiographyContract.Columns.RELATIVES)
    val relatives: String
)