package innovappte.mobile.domain.repositories

import innovappte.mobile.domain.models.FiFaCelebration
import io.reactivex.Completable
import io.reactivex.Single

interface FiFaCelebrationsRepositoy {
    fun getFiFaCelebrations(): Single<List<FiFaCelebration>>
    fun downloadCelebrationsVideos(celebrations: List<FiFaCelebration>)
}