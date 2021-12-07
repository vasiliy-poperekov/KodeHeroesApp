package com.example.kodeheroesapp.domain.use_cases

import com.example.kodeheroesapp.domain.HeroRepository

class RefreshHeroListUseCase(
    private val heroRepository: HeroRepository
) {
    suspend operator fun invoke() = heroRepository.refreshList()
}