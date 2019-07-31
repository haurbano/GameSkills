package innovappte.mobile.data.di

import com.google.firebase.firestore.FirebaseFirestore
import innovappte.mobile.data.datasources.VideosDataSource
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
    factory { VideosDataSource(androidContext()) }
}