package com.example.kodeheroesapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShortcutBiography(
    val fullName: String,
    val publisher: String?
)