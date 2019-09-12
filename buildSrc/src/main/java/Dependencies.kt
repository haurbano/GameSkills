import Versions.app_compat_version
import Versions.constrain_layout_version
import Versions.fire_store_version
import Versions.firebase_auth_version
import Versions.firebase_core_version
import Versions.firebase_database
import Versions.koin_version
import Versions.kotlin_version
import Versions.videoPlayerToroVersion

object Versions {
    const val kotlin_version            = "1.3.41"
    const val app_compat_version        = "28.0.0"
    const val koin_version              = "2.0.1"
    const val firebase_core_version     = "17.0.0"
    const val firebase_database         = "18.0.0"
    const val constrain_layout_version  = "2.0.0-alpha3"
    const val fire_store_version        = "20.1.0"
    const val videoPlayerToroVersion    = "3.6.2.2903"
    const val firebase_auth_version     = "19.0.0"
}

object Dependencies {
    val kotlin              =    "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    val appCompat           =    "com.android.support:appcompat-v7:$app_compat_version"
    val constrainLayout     =    "androidx.constraintlayout:constraintlayout:$constrain_layout_version"
    val gson                =    "com.google.code.gson:gson:2.8.5"
    val koin                =    "org.koin:koin-android:$koin_version"
    val koinViewModel       =    "org.koin:koin-android-viewmodel:$koin_version"
    val rxAndroid           =    "io.reactivex.rxjava2:rxandroid:2.1.1"
    val rxJava              =    "io.reactivex.rxjava2:rxjava:2.1.1"
    val recyclerView        =    "com.android.support:recyclerview-v7:28.0.0"
    val firebaseCore        =    "com.google.firebase:firebase-core:$firebase_core_version"
    val firebaseDatabase    =    "com.google.firebase:firebase-database:$firebase_database"
    val fireStore           =    "com.google.firebase:firebase-firestore:$fire_store_version"
    val videoPlayerToro     =       "im.ene.toro3:toro:$videoPlayerToroVersion"
    val videoPlayerToroExoPlayer =  "im.ene.toro3:toro-ext-exoplayer:$videoPlayerToroVersion"
    val firebaseAuth        =   "com.google.firebase:firebase-auth:$firebase_auth_version"
}

object TestDependencies {
    val junit               =    "junit:junit:4.12"
}

object AndroidTestDependencies {
    val runner              =      "com.android.support.test:runner:1.0.2"
    val espresso            =      "com.android.support.test.espresso:espresso-core:3.0.2"
}