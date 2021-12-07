package com.example.kodeheroesapp.domain.use_cases

import com.example.kodeheroesapp.domain.HeroRepository

class GetHeroBiographyUseCase(
    private val heroRepository: HeroRepository
) {
    operator fun invoke(id: Int) = heroRepository.getBiography(id)
}