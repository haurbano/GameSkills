package innovappte.mobile.data.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseDatabase.getInstance() }
}