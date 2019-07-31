package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.FiFaCelebrationsFirebaseDataSource
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.data.mappers.FiFaCelebrationMapper
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import io.reactivex.Single

class FiFaCelebrationsRepositoyImpl(
        private val videosDataSource: VideosDataSource,
        private val fiFaCelebrationsFirebaseDataSource: FiFaCelebrationsFirebaseDataSource,
        private val fiFaCelebrationMapper: FiFaCelebrationMapper
): FiFaCelebrationsRepositoy {
    override fun getFiFaCelebrations(): Single<List<FiFaCelebration>> {
        return fiFaCelebrationsFirebaseDataSource.getFiFaCelebrations()
                .map { fiFaCelebrationMapper(it) }
    }

    override fun downloadCelebrationsVideos(celebrations: List<FiFaCelebration>) {
        val videos = celebrations.map { it.getVideosToDownloadList() }.flatten()
        videos.forEach { video ->
            videosDataSource.downloadVideo(video.url, video.getTargetFileName())
        }
    }
}