package innovappte.mobile.data.services

class DownloadVideoServiceProvider(
        private val retrofitClientProvider: RetrofitClientProvider
) {
    fun get(): DownloadVideoService {
        return  retrofitClientProvider.get()
                .create(DownloadVideoService::class.java)
    }
}