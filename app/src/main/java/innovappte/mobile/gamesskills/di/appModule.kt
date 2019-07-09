package innovappte.mobile.gamesskills.di

import com.hamilton.gamesskillst.data.DataFilesManager
import com.hamilton.gamesskillst.data.GameSkillRepositoryImpl
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.data.FifaSharedPreferenceHandler
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.domain.usecases.impl.FifaConfigUseCaseImpl
import innovappte.mobile.gamesskills.domain.usecases.impl.FifaGameSkillsUseCaseImpl
import innovappte.mobile.gamesskills.presentation.fifa.home.FifaHomeVM
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillsListVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    // region Fifa
    viewModel { FifaSkillsListVM(get()) }
    viewModel { FifaHomeVM(get()) }
    factory<FifaGameSkillsUseCase> { FifaGameSkillsUseCaseImpl(get()) }
    factory<GameSkillsRepository> { GameSkillRepositoryImpl(get(), androidContext()) }
    factory { DataFilesManager(androidContext()) }
    factory<FifaConfigUseCase> { FifaConfigUseCaseImpl(get()) }
    factory { FifaSharedPreferenceHandler(androidContext()) }
    //endregion
}