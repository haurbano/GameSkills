package innovappte.mobile.domain.usecases.impl

import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import innovappte.mobile.domain.usecases.interfaces.GetFifaCelebrationUseCase
import io.reactivex.Single

class GetFifaCelebrationUseCaseImpl(
        private val fiFaCelebrationsRepository: FiFaCelebrationsRepositoy
): GetFifaCelebrationUseCase {

    override fun invoke(celebrationID: String): Single<FiFaCelebration> {
        return fiFaCelebrationsRepository.getCelebration(celebrationID)
    }
}