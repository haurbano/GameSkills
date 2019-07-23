package innovappte.mobile.data.di

import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module

val dataModule = module {
    single { FirebaseFirestore.getInstance() }
}