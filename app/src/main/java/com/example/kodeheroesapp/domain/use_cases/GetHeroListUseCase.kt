package com.example.kodeheroesapp.domain.use_cases

import com.example.kodeheroesapp.domain.HeroRepository

class GetHeroListUseCase(
    private val heroRepository: HeroRepository
) {
    operator fun invoke() = heroRepository.getList()
}