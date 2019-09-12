package innovappte.mobile.gamesskills.di

import innovappte.mobile.data.repositories.AuthenticationRepositoryImpl
import innovappte.mobile.data.repositories.FiFaCelebrationsRepositoryImpl
import innovappte.mobile.data.repositories.FiFaSharedPreferenceRepositoryImpl
import innovappte.mobile.data.repositories.GameSkillRepositoryImpl
import innovappte.mobile.domain.repositories.AuthenticationRepository
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import innovappte.mobile.domain.repositories.FiFaSharedPreferenceRepository
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import org.koin.dsl.module

val respositoriesModule = module {
    factory<GameSkillsRepository> { GameSkillRepositoryImpl(get(), get()) }
    factory<FiFaCelebrationsRepositoy> { FiFaCelebrationsRepositoryImpl(get(), get(), get()) }
    factory<FiFaSharedPreferenceRepository> { FiFaSharedPreferenceRepositoryImpl(get()) }
    factory<AuthenticationRepository> { AuthenticationRepositoryImpl(get()) }
}