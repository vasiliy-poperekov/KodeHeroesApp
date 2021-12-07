package com.example.kodeheroesapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullHeroResponse(
    val id: Int,
    val name: String,
    val biography: FullBiography,
    val connections: Connections,
    val images: Images
)
