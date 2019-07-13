package innovappte.mobile.gamesskills.presentation.fifa.skills

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
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
        gameSkillsUseCase.getGameSkills{ gameSkillsResponse ->
                    gameSkills.value = gameSkillsResponse
                    gameSkillsUseCase.downloadSkillVideos(gameSkillsResponse)
                }
    }
}