package com.example.kodeheroesapp.presentation

import android.app.Application
import androidx.room.Room
import com.example.kodeheroesapp.BuildConfig
import com.example.kodeheroesapp.data.AppDatabase
import com.example.kodeheroesapp.data.HeroRepositoryImpl
import com.example.kodeheroesapp.data.HeroRetrofitImpl
import com.example.kodeheroesapp.domain.use_cases.GetHeroBiographyUseCase
import com.example.kodeheroesapp.domain.use_cases.GetHeroListUseCase
import com.example.kodeheroesapp.domain.use_cases.RefreshHeroBiographyUseCase
import com.example.kodeheroesapp.domain.use_cases.RefreshHeroListUseCase
import com.example.kodeheroesapp.presentation.utils.NetworkingChecker
import com.example.kodeheroesapp.presentation.view_models.BiographyFragmentViewModel
import com.example.kodeheroesapp.presentation.view_models.MainFragmentViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import timber.log.Timber

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    localModule,
                    daosModule,
                    remoteModule,
                    useCasesModule,
                    viewModelsModule
                )
            )
        }
    }

    private val localModule = module {
        single {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                AppDatabase.DB_NAME
            ).build()
        }
    }

    private val daosModule = module {
        factory { get<AppDatabase>().getHeroDao() }
        factory { get<AppDatabase>().getHeroBiographyDao() }
    }

    private val remoteModule = module {
        factory { HeroRetrofitImpl() }
        factory { HeroRepositoryImpl(get<HeroRetrofitImpl>().heroApi, get(), get()) }
        factory { NetworkingChecker(this@MainApplication) }
    }

    private val useCasesModule = module {
        factory { GetHeroListUseCase(get<HeroRepositoryImpl>()) }
        factory { GetHeroBiographyUseCase(get<HeroRepositoryImpl>()) }
        factory { RefreshHeroListUseCase(get<HeroRepositoryImpl>()) }
        factory { RefreshHeroBiographyUseCase(get<HeroRepositoryImpl>()) }
    }

    private val viewModelsModule = module {
        viewModel { MainFragmentViewModel(get(), get(), get()) }
        viewModel { BiographyFragmentViewModel(get(), get(), get()) }
    }
}