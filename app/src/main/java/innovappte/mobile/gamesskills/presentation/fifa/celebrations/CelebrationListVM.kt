package innovappte.mobile.gamesskills.presentation.fifa.celebrations

import androidx.lifecycle.ViewModel
import innovappte.mobile.common.L
import innovappte.mobile.domain.usecases.interfaces.FiFaCelebrationUseCase
import io.reactivex.disposables.CompositeDisposable

class CelebrationListVM(
        private val celebrationUseCase: FiFaCelebrationUseCase
): ViewModel() {

    private val disposables = CompositeDisposable()

    fun prepareCelebrationData() {
        val disposable = celebrationUseCase.getCelebrations().subscribe { celebrations ->
            L.i("Celebrations: $celebrations")
            celebrationUseCase.downloadCelebrationsVideos(celebrations)
        }
        disposables.add(disposable)
    }
}