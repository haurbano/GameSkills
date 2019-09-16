package innovappte.mobile.gamesskills.presentation.fifa.celebrationdetail

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.common.L
import innovappte.mobile.domain.models.Action
import innovappte.mobile.domain.models.FiFaCelebration
import innovappte.mobile.domain.usecases.interfaces.GetFifaCelebrationUseCase
import innovappte.mobile.domain.usecases.interfaces.SelectVideoTypeUseCase
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaConfigUseCase
import io.reactivex.disposables.CompositeDisposable

class CelebrationDetailVM(
        private val getFifaCelebrationUseCase: GetFifaCelebrationUseCase,
        private val fifaConfigUseCase: FifaConfigUseCase,
        private val selectVideoTypeUseCase: SelectVideoTypeUseCase
): ViewModel() {

    private val disposables = CompositeDisposable()

    val celebrationVideoUri: MutableLiveData<Uri> = MutableLiveData()
    val controlVideo: MutableLiveData<Uri> = MutableLiveData()
    val celebrationSteps: MutableLiveData<List<Action>> = MutableLiveData()
    val celebration: MutableLiveData<FiFaCelebration> = MutableLiveData()

    fun prepareData(celebrationId: String) {
        val disposable = getFifaCelebrationUseCase(celebrationId)
                .subscribe({
                    celebration.value = it
                    prepareCelebrationVideo(it)
                    prepareControlVideo(it)
                }, { error -> L.e(error) })

        disposables.add(disposable)
    }

    private fun prepareCelebrationVideo(celebration: FiFaCelebration) {

    }

    private fun prepareControlVideo(celebration: FiFaCelebration) {

    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        disposables.clear()
    }
}