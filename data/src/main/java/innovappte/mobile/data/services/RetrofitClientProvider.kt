package innovappte.mobile.data.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClientProvider {
    companion object {
        const val BASE_URL = "https://firebasestorage.googleapis.com/v0/b/gameskillsapp-38862.appspot.com/"
    }
    fun get(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }
}