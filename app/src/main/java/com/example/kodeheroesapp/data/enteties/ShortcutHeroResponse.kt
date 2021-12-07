package com.example.kodeheroesapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShortcutHeroResponse(
    val id: Int,
    val name: String,
    val biography: ShortcutBiography,
    val images: Images
)