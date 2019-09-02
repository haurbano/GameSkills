package innovappte.mobile.domain.usecases.interfaces

import innovappte.mobile.domain.models.FiFaCelebration
import io.reactivex.Single

interface FiFaCelebrationUseCase {
    fun getCelebrations(): Single<List<FiFaCelebration>>
    fun downloadCelebrationsVideos(celebrations: List<FiFaCelebration>)
}