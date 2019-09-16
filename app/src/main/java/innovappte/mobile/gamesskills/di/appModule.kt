package innovappte.mobile.gamesskills.di

import innovappte.mobile.data.DataFilesManager
import innovappte.mobile.data.VideoPathUtils
import innovappte.mobile.data.datasources.ActionsDataSource
import innovappte.mobile.data.datasources.FiFaCelebrationsFirebaseDataSource
import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.datasources.FifaSharedPreferenceDataSource
import innovappte.mobile.data.mappers.ActionMapper
import innovappte.mobile.data.mappers.FiFaCelebrationMapper
import innovappte.mobile.data.mappers.GameSkillsMapper
import innovappte.mobile.gamesskills.presentation.fifa.celebrations.CelebrationListVM
import innovappte.mobile.gamesskills.presentation.fifa.home.FifaHomeVM
import innovappte.mobile.gamesskills.presentation.fifa.skilldetail.SkillDetailsVM
import innovappte.mobile.gamesskills.presentation.fifa.skills.FifaSkillsListVM
import innovappte.mobile.gamesskills.presentation.splash.SplashVM
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val appModule: Module = module(override = true) {
    // region Fifa
    factory { GameSkillsMapper() }
    factory { FiFaGameSkillsFirebaseDataSource(get(), get()) }
    factory { DataFilesManager(androidContext()) }
    factory { FifaSharedPreferenceDataSource(androidContext()) }
    factory { FiFaCelebrationMapper() }
    factory { FiFaCelebrationsFirebaseDataSource(get(), get()) }
    factory { ActionsDataSource(get(), get()) }
    factory { ActionMapper() }
    factory { VideoPathUtils(androidContext()) }
    //endregion
}

val viewModelModule = module {
    viewModel { FifaSkillsListVM(get()) }
    viewModel { FifaHomeVM(get()) }
    viewModel { CelebrationListVM(get()) }
    viewModel { SplashVM(get()) }
    viewModel { SkillDetailsVM(get(),get(),get()) }
}