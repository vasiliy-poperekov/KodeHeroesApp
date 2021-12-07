package com.example.kodeheroesapp.data.converters

import androidx.room.TypeConverter

class AliasesConverter {
    @TypeConverter
    fun fromAliases(aliasesList: List<String>) =
        aliasesList.joinToString(",")

    @TypeConverter
    fun toAliases(aliases: String) =
        aliases.split(",")
}