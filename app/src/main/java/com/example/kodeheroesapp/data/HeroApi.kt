package com.example.kodeheroesapp.data

import com.example.kodeheroesapp.data.enteties.FullHeroResponse
import com.example.kodeheroesapp.data.enteties.ShortcutHeroResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HeroApi {
    @GET("all.json")
    suspend fun getAll(): List<ShortcutHeroResponse>

    @GET("id/{hero_id}.json")
    suspend fun get(
        @Path("hero_id") id: Int
    ): FullHeroResponse
}