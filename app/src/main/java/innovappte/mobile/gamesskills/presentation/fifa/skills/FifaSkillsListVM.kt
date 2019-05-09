package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.Interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.presentation.common.ViewStatus
import io.reactivex.disposables.CompositeDisposable


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {
    val gameSkills: MutableLiveData<List<GameSkill>> =
            MutableLiveData<List<GameSkill>>().apply { value = emptyList() }

    var loaderViewStatus: MutableLiveData<ViewStatus> =
            MutableLiveData<ViewStatus>().apply{ value = ViewStatus.HIDDEN }

    private val disposables = CompositeDisposable()

    fun prepareGameSkillsData() {
        loaderViewStatus.value = ViewStatus.SHOWED
        val disposable = gameSkillsUseCase.getGameSkills().subscribe(
                { gameSkillsResponse ->
                    gameSkills.value = gameSkillsResponse
                    gameSkillsUseCase.downloadSkillVideos(gameSkillsResponse)
                },
                { error -> Log.e("ERROR", "Error getting Fifa Game Skills") }
        )

        disposables.add(disposable)
    }
}