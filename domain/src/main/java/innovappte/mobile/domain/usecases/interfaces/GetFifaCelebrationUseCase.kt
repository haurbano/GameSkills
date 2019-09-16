package innovappte.mobile.domain.usecases.interfaces

import innovappte.mobile.domain.models.FiFaCelebration
import io.reactivex.Single

interface GetFifaCelebrationUseCase {
    operator fun invoke(celebrationID: String): Single<FiFaCelebration>
}