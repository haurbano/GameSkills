package innovappte.mobile.gamesskills.di

import com.hamilton.gamesskillst.data.DataFilesManager
import com.hamilton.gamesskillst.data.GameSkillRepositoryImpl
import com.hamilton.gamesskillst.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.domain.usecases.Interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.domain.usecases.impl.FifaGameSkillsUseCaseImpl
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillsListVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel

import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    // region Fifa
    viewModel { FifaSkillsListVM(get()) }
    factory<FifaGameSkillsUseCase> { FifaGameSkillsUseCaseImpl(get()) }
    factory<GameSkillsRepository> { GameSkillRepositoryImpl(get()) }
    factory { DataFilesManager(androidContext()) }
    //endregion
}