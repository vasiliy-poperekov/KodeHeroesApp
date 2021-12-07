package com.example.kodeheroesapp.data

import com.example.kodeheroesapp.data.daos.HeroBiographyDao
import com.example.kodeheroesapp.data.daos.HeroDao
import com.example.kodeheroesapp.data.enteties.HeroBiographyEntity
import com.example.kodeheroesapp.data.enteties.HeroEntity
import com.example.kodeheroesapp.domain.HeroRepository
import com.example.kodeheroesapp.domain.enteties.Hero
import com.example.kodeheroesapp.domain.enteties.HeroBiography
import kotlinx.coroutines.flow.*
import timber.log.Timber

class HeroRepositoryImpl(
    private val heroApi: HeroApi,
    private val heroDao: HeroDao,
    private val heroBiographyDao: HeroBiographyDao
) : HeroRepository {

    override fun getList(): Flow<List<Hero>?> = heroDao.getHeroEntitiesList().map { heroList ->
        heroList?.map { heroEntity ->
            Hero(
                id = heroEntity.id,
                nickName = heroEntity.nickName,
                realName = heroEntity.realName,
                publisher = heroEntity.publisher,
                imageUrl = heroEntity.imageUrl
            )
        }
    }

    override suspend fun refreshList() {
        kotlin.runCatching {
            heroApi.getAll()
        }.onSuccess { heroesList ->
            heroDao.addListHeroEntity(heroesList.map { shortcutHero ->
                HeroEntity(
                    id = shortcutHero.id,
                    nickName = shortcutHero.name,
                    realName = shortcutHero.biography.fullName,
                    publisher = shortcutHero.biography.publisher ?: "",
                    imageUrl = shortcutHero.images.md
                )
            })
        }.onFailure { exception ->
            Timber.e(exception)
        }
    }

    override fun getBiography(id: Int): Flow<HeroBiography?> = flow {
        heroBiographyDao.getHeroEntitiesList(id).collect { heroBiographyEntity ->
            if (heroBiographyEntity != null) {
                emit(
                    HeroBiography(
                        hero = heroBiographyEntity.hero,
                        aliases = heroBiographyEntity.aliases,
                        placeOfBirth = heroBiographyEntity.placeOfBirth,
                        relatives = heroBiographyEntity.relatives
                    )
                )
            } else emit(null)
        }
    }

    override suspend fun refreshBiography(id: Int) {
        kotlin.runCatching {
            heroApi.get(id)
        }.onSuccess { fullHero ->
            heroBiographyDao.addHeroBiographyEntity(
                HeroBiographyEntity(
                    id = fullHero.id,
                    hero = Hero(
                        id = fullHero.id,
                        nickName = fullHero.name,
                        realName = fullHero.biography.fullName,
                        publisher = fullHero.biography.publisher ?: "",
                        imageUrl = fullHero.images.md
                    ),
                    aliases = fullHero.biography.aliases,
                    placeOfBirth = fullHero.biography.placeOfBirth,
                    relatives = fullHero.connections.relatives
                )
            )
        }.onFailure { exception ->
            Timber.e(exception)
        }
    }
}