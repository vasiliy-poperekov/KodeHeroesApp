package com.example.kodeheroesapp.presentation.view_models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kodeheroesapp.domain.enteties.HeroBiography
import com.example.kodeheroesapp.domain.use_cases.GetHeroBiographyUseCase
import com.example.kodeheroesapp.domain.use_cases.RefreshHeroBiographyUseCase
import com.example.kodeheroesapp.presentation.utils.NetworkingChecker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class BiographyFragmentViewModel(
    private val getHeroBiographyUseCase: GetHeroBiographyUseCase,
    private val refreshHeroBiographyUseCase: RefreshHeroBiographyUseCase,
    private val networkingChecker: NetworkingChecker
) : ViewModel() {
    private val heroBiographyMutStateFlow = MutableStateFlow<HeroBiography?>(null)

    val heroBiographyFlow: Flow<HeroBiography?>
        get() = heroBiographyMutStateFlow

    val connectionFlow = networkingChecker.observeNetworkingChanges()

    fun getHeroBiography(id: Int) {
        viewModelScope.launch {

            launch {
                networkingChecker.observeNetworkingChanges().collect { isConnected ->
                    if (isConnected) refreshHeroBiographyUseCase(id)
                }
            }

            launch {
                getHeroBiographyUseCase(id).collect { heroBiography ->
                    heroBiographyMutStateFlow.emit(heroBiography)
                }
            }
        }
    }
}