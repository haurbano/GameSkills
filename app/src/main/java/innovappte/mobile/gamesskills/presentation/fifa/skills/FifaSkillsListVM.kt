package innovappte.mobile.gamesskills.presentation.fifa.skills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import innovappte.mobile.common.L
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import io.reactivex.disposables.CompositeDisposable


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {
    val gameSkills: MutableLiveData<List<GameSkill>> =
            MutableLiveData<List<GameSkill>>().apply { value = arrayListOf() }


    private val disposables = CompositeDisposable()

    fun prepareGameSkillsData() {

        val disposable = gameSkillsUseCase.getGameSkills().subscribe({ gameSkillsList ->
            gameSkills.value = gameSkillsList
            gameSkillsUseCase.downloadSkillVideos(gameSkillsList)
        }, { error -> L.e(error)})
        disposables.add(disposable)
    }
}