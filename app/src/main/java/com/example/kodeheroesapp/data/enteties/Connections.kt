package com.example.kodeheroesapp.data.enteties

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Connections(
    val relatives: String
)