package com.example.kodeheroesapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FullBiography(
    val fullName: String,
    val aliases: List<String>,
    val placeOfBirth: String,
    val publisher: String?
)
