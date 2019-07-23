package innovappte.mobile.gamesskills.presentation.fifa.skills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import innovappte.mobile.gamesskills.presentation.common.ViewStatus
import io.reactivex.disposables.CompositeDisposable


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {
    val gameSkills: MutableLiveData<List<GameSkill>> =
            MutableLiveData<List<GameSkill>>().apply { value = arrayListOf() }

    var loaderViewStatus: MutableLiveData<ViewStatus> =
            MutableLiveData<ViewStatus>().apply{ value = ViewStatus.HIDDEN }

    private val disposables = CompositeDisposable()

    fun prepareGameSkillsData() {
        loaderViewStatus.value = ViewStatus.SHOWED
        val disposable = gameSkillsUseCase.getGameSkills().subscribe { gameSkillsList ->
                    gameSkills.value = gameSkillsList
                    gameSkillsUseCase.downloadSkillVideos(gameSkillsList)
                }
        disposables.add(disposable)
    }
}