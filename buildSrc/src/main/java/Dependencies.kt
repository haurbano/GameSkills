import Versions.app_compat_version
import Versions.koinVersion
import Versions.kotlin_version

object Versions {
    const val kotlin_version = "1.3.41"
    const val app_compat_version = "28.0.0"
    const val koinVersion = "2.0.1"
}

object Dependencies {
    val kotlin          =    "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlin_version"
    val appCompat       =    "com.android.support:appcompat-v7:$app_compat_version"
    val constrainLayout =    "com.android.support.constraint:constraint-layout:1.1.3"
    val gson            =    "com.google.code.gson:gson:2.8.5"
    val koin            =    "org.koin:koin-android:$koinVersion"
    val koinViewModel   =    "org.koin:koin-android-viewmodel:$koinVersion"
    val rxAndroid       =    "io.reactivex.rxjava2:rxandroid:2.1.1"
    val rxJava          =    "io.reactivex.rxjava2:rxjava:2.1.1"
    val recyclerView    =    "com.android.support:recyclerview-v7:28.0.0"
}

object TestDependencies {
    val junit            =    "junit:junit:4.12"
}

object AndroidTestDependencies {
    val runner          =      "com.android.support.test:runner:1.0.2"
    val espresso        =      "com.android.support.test.espresso:espresso-core:3.0.2"
}