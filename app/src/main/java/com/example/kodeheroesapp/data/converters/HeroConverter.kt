package com.example.kodeheroesapp.data.converters

import androidx.room.TypeConverter
import com.example.kodeheroesapp.domain.enteties.Hero

class HeroConverter {
    @TypeConverter
    fun fromHero(hero: Hero) =
        "${hero.id},${hero.nickName},${hero.realName},${hero.publisher},${hero.imageUrl}"

    @TypeConverter
    fun toHero(heroString: String) =
        Hero(
            id = heroString.split(",")[0].toInt(),
            nickName = heroString.split(",")[1],
            realName = heroString.split(",")[2],
            publisher = heroString.split(",")[3],
            imageUrl = heroString.split(",")[4]
        )
}