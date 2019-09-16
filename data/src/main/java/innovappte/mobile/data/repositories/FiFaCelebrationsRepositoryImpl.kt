package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.ActionsDataSource
import innovappte.mobile.data.datasources.FiFaCelebrationsFirebaseDataSource
import innovappte.mobile.data.datasources.FiFaFirebaseCollections.CELEBRATIONS
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.domain.models.Action
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import io.reactivex.Observable
import io.reactivex.Single

class FiFaCelebrationsRepositoryImpl(
        private val videosDataSource: VideosDataSource,
        private val fiFaCelebrationsFirebaseDataSource: FiFaCelebrationsFirebaseDataSource,
        private val actionsDataSource: ActionsDataSource
): FiFaCelebrationsRepositoy {

    override fun getFiFaCelebrations(): Single<List<FiFaCelebration>> {
        return fiFaCelebrationsFirebaseDataSource.getFiFaCelebrations()
                .flattenAsObservable { it }
                .flatMap({getActions(it.id)}, { celebration, retrievedActions ->
                       celebration.apply { actions = retrievedActions }
                }).toList()
    }

    private fun getActions(documentId: String): Observable<List<Action>> {
        return actionsDataSource.getActions(CELEBRATIONS, documentId).toObservable()
                .map { actions -> actions.sortedBy { it.index } }
    }

    override fun downloadCelebrationsVideos(celebrations: List<FiFaCelebration>) {
        val videos = celebrations.map { it.getVideosToDownloadList() }.flatten()
        videos.forEach { video ->
            videosDataSource.downloadVideo(video.url, video.getTargetFileName())
        }
    }

    override fun getCelebration(celebrationId: String): Single<FiFaCelebration> {
        return fiFaCelebrationsFirebaseDataSource.getFiFaCelebration(celebrationId)
    }
}