package innovappte.mobile.domain.usecases.impl

import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.repositories.FiFaCelebrationsRepositoy
import innovappte.mobile.domain.usecases.interfaces.FiFaCelebrationUseCase
import io.reactivex.Single

class FiFaCelebrationUseCaseImpl(
        private val celebrationsRepository: FiFaCelebrationsRepositoy
): FiFaCelebrationUseCase {
    override fun getCelebrations(): Single<List<FiFaCelebration>> {
        return celebrationsRepository.getFiFaCelebrations()
    }

    override fun downloadCelebrationsVideos(celebrations: List<FiFaCelebration>) {
        celebrationsRepository.downloadCelebrationsVideos(celebrations)
    }
}