package innovappte.mobile.gamesskills.di

import com.hamilton.gamesskillst.data.DataFilesManager
import com.hamilton.gamesskillst.data.GameSkillRepositoryImpl
import com.hamilton.gamesskillst.domain.repositories.GameSkillsRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    factory<GameSkillsRepository> { GameSkillRepositoryImpl(get()) }
    factory { DataFilesManager(androidContext()) }
}