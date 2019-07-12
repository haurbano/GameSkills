package innovappte.mobile.gamesskills.presentation

import android.app.Application
import innovappte.mobile.gamesskills.di.appModule
import innovappte.mobile.gamesskills.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GamesSkillsApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GamesSkillsApp)
            modules(listOf(appModule, viewModelModule))
        }
    }
}