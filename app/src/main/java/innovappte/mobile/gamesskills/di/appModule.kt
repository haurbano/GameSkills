package innovappte.mobile.gamesskills.di

import innovappte.mobile.data.repositories.GameSkillRepositoryImpl
import innovappte.mobile.data.datasources.FifaSharedPreferenceDataSource
import innovappte.mobile.domain.usecases.impl.FifaConfigUseCaseImpl
import innovappte.mobile.data.DataFilesManager
import innovappte.mobile.data.datasources.FiFaCelebrationsFirebaseDataSource
import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.mappers.FiFaCelebrationMapper
import innovappte.mobile.data.mappers.GameSkillsMapper
import innovappte.mobile.data.repositories.FiFaCelebrationsRepositoyImpl
import innovappte.mobile.data.repositories.FiFaSharedPreferenceRepositoryImpl
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import innovappte.mobile.domain.repositories.FiFaSharedPreferenceRepository
import innovappte.mobile.domain.usecases.impl.FiFaCelebrationUseCaseImpl
import innovappte.mobile.domain.usecases.interfaces.FiFaCelebrationUseCase
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.domain.usecases.impl.FifaGameSkillsUseCaseImpl
import innovappte.mobile.gamesskills.presentation.fifa.celebrations.CelebrationListVM
import innovappte.mobile.gamesskills.presentation.fifa.home.FifaHomeVM
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillsListVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module(override = true) {
    // region Fifa
    factory<FifaGameSkillsUseCase> { FifaGameSkillsUseCaseImpl(get()) }
    factory<GameSkillsRepository> { GameSkillRepositoryImpl(get(), get(), get()) }
    factory { GameSkillsMapper() }
    factory { FiFaGameSkillsFirebaseDataSource(get()) }
    factory { DataFilesManager(androidContext()) }

    factory<FifaConfigUseCase> { FifaConfigUseCaseImpl(get()) }
    factory<FiFaSharedPreferenceRepository> { FiFaSharedPreferenceRepositoryImpl(get()) }
    factory { FifaSharedPreferenceDataSource(androidContext()) }


    factory<FiFaCelebrationUseCase> { FiFaCelebrationUseCaseImpl(get()) }
    factory<FiFaCelebrationsRepositoy> { FiFaCelebrationsRepositoyImpl(get(), get(), get()) }
    factory { FiFaCelebrationMapper() }
    factory { FiFaCelebrationsFirebaseDataSource(get()) }
    //endregion
}

val viewModelModule = module {
    viewModel { FifaSkillsListVM(get()) }
    viewModel { FifaHomeVM(get()) }
    viewModel { CelebrationListVM(get()) }
}