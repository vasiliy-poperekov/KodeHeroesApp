package com.example.kodeheroesapp.data.contracts

object HeroBiographyContract {
    const val TABLE_NAME = "hero_biography"

    object Columns {
        const val ID = "id"
        const val HERO = "hero"
        const val ALIASES = "aliases"
        const val PLACE_OF_BIRTH = "placeOfBirth"
        const val RELATIVES = "relatives"
    }
}