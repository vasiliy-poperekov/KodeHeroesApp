package com.example.kodeheroesapp.domain.use_cases

import com.example.kodeheroesapp.domain.HeroRepository

class RefreshHeroBiographyUseCase(
    private val heroRepository: HeroRepository
) {
    suspend operator fun invoke(id: Int) = heroRepository.refreshBiography(id)
}