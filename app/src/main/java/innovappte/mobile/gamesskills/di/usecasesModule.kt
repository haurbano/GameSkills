package innovappte.mobile.gamesskills.di

import innovappte.mobile.domain.usecases.impl.AuthenticateUserUseCaseImpl
import innovappte.mobile.domain.usecases.impl.FiFaCelebrationUseCaseImpl
import innovappte.mobile.domain.usecases.impl.FifaConfigUseCaseImpl
import innovappte.mobile.domain.usecases.impl.SelectVideoTypeUseCaseImpl
import innovappte.mobile.domain.usecases.interfaces.AuthenticateUserUseCase
import innovappte.mobile.domain.usecases.interfaces.FiFaCelebrationUseCase
import innovappte.mobile.domain.usecases.interfaces.SelectVideoTypeUseCase
import innovappte.mobile.gamesskills.domain.usecases.impl.FifaGameSkillsUseCaseImpl
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val useCasesModule: Module = module(override = true) {
    factory<FifaGameSkillsUseCase> { FifaGameSkillsUseCaseImpl(get()) }
    factory<FifaConfigUseCase> { FifaConfigUseCaseImpl(get()) }
    factory<FiFaCelebrationUseCase> { FiFaCelebrationUseCaseImpl(get()) }
    factory<AuthenticateUserUseCase> { AuthenticateUserUseCaseImpl(get()) }
    factory<SelectVideoTypeUseCase> { SelectVideoTypeUseCaseImpl() }
}