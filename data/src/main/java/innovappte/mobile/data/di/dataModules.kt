package innovappte.mobile.data.di

import com.google.firebase.firestore.FirebaseFirestore
import innovappte.mobile.authentication.Authenticator
import innovappte.mobile.authentication.FirebaseAuthenticator
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.data.services.DownloadVideoServiceProvider
import innovappte.mobile.data.services.RetrofitClientProvider
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
    factory { VideosDataSource(get(), get()) }
    single<Authenticator> { FirebaseAuthenticator() }
    single { RetrofitClientProvider() }
    single { DownloadVideoServiceProvider(get()) }
}