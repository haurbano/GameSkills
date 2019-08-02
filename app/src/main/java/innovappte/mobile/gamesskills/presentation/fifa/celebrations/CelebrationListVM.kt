package innovappte.mobile.gamesskills.presentation.fifa.celebrations

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.usecases.interfaces.FiFaCelebrationUseCase
import io.reactivex.disposables.CompositeDisposable

class CelebrationListVM(
        private val celebrationUseCase: FiFaCelebrationUseCase
): ViewModel() {

    val celebrationList: MutableLiveData<List<FiFaCelebration>> =
            MutableLiveData<List<FiFaCelebration>>().apply { value = arrayListOf() }

    private val disposables = CompositeDisposable()

    fun prepareCelebrationData() {
        val disposable = celebrationUseCase.getCelebrations().subscribe { celebrations ->
            celebrationList.value = celebrations
            celebrationUseCase.downloadCelebrationsVideos(celebrations)
        }
        disposables.add(disposable)
    }
}