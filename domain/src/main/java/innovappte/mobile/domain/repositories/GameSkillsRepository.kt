package innovappte.mobile.gamesskills.domain.repositories

import innovappte.mobile.domain.models.GameSkill
import io.reactivex.Single

interface GameSkillsRepository {
    fun getFifaGameSkills(): Single<List<GameSkill>>
    fun downloadSkillsVideos(skills: List<GameSkill>)
}