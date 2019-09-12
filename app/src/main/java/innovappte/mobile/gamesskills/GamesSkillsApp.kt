package innovappte.mobile.gamesskills

import android.app.Application
import innovappte.mobile.data.di.dataModule
import innovappte.mobile.gamesskills.di.appModule
import innovappte.mobile.gamesskills.di.repositoriesModule
import innovappte.mobile.gamesskills.di.useCasesModule
import innovappte.mobile.gamesskills.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GamesSkillsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GamesSkillsApp)
            modules(listOf(
                    appModule,
                    viewModelModule,
                    dataModule,
                    useCasesModule,
                    repositoriesModule
            ))
        }
    }
}