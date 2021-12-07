package com.example.kodeheroesapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kodeheroesapp.domain.enteties.Hero
import com.example.kodeheroesapp.domain.use_cases.GetHeroListUseCase
import com.example.kodeheroesapp.domain.use_cases.RefreshHeroListUseCase
import com.example.kodeheroesapp.presentation.utils.NetworkingChecker
import com.example.kodeheroesapp.presentation.utils.StringHelper.ALL
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainFragmentViewModel(
    private val getHeroListUseCase: GetHeroListUseCase,
    private val refreshHeroListUseCase: RefreshHeroListUseCase,
    private val networkingChecker: NetworkingChecker
) : ViewModel() {
    private val heroesListMutStateFlow = MutableStateFlow<List<Hero>?>(null)
    private val changedHeroesListMutStateFlow = MutableStateFlow<List<Hero>?>(null)

    val heroesListFlow: Flow<List<Hero>?>
        get() = heroesListMutStateFlow

    val changedHeroesListFlow: Flow<List<Hero>?>
        get() = changedHeroesListMutStateFlow

    val networkingCheckerFlow = networkingChecker.observeNetworkingChanges()

    init {
        viewModelScope.launch {

            launch {
                networkingChecker.observeNetworkingChanges().collect { isConnected ->
                    if (isConnected) refreshHeroListUseCase()
                }
            }

            launch {
                getHeroListUseCase().collect { heroList ->
                    heroesListMutStateFlow.emit(heroList)
                }
            }
        }
    }

    fun filterHeroesList(parameter: String) {
        viewModelScope.launch {
            changedHeroesListMutStateFlow.emit(
                heroesListMutStateFlow.value?.filter { hero ->
                    if (parameter != ALL) hero.publisher == parameter
                    else true
                }
            )
        }
    }
}