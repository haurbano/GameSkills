package innovappte.mobile.gamesskills.presentation.fifa.skills

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.hamilton.gamesskillst.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.usecases.Interfaces.FifaGameSkillsUseCase


class FifaSkillsListVM(private val gameSkillsUseCase: FifaGameSkillsUseCase) : ViewModel() {
    val gameSkills: MutableLiveData<List<GameSkill>> = MutableLiveData<List<GameSkill>>().apply { value = emptyList() }

    init {
        loadGameSkills()
    }

    private fun loadGameSkills() {
        gameSkillsUseCase.getGameSkills().subscribe(
                { gameSkillsResponse -> gameSkills.value = gameSkillsResponse },
                { error -> Log.e("ERROR", "Error getting Fifa Game Skills") }
        )
    }
}