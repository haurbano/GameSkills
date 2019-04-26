package innovappte.mobile.gamesskills.domain.usecases.Interfaces

import com.hamilton.gamesskillst.domain.models.GameSkill
import io.reactivex.Single

interface FifaGameSkillsUseCase {
    fun getGameSkills(): Single<List<GameSkill>>
}