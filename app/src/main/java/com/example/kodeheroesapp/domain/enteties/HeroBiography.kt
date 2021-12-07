package com.example.kodeheroesapp.domain.enteties

data class HeroBiography(
    val hero: Hero,
    val aliases: List<String>,
    val placeOfBirth: String,
    val relatives: String,
)