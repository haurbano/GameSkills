package innovappte.mobile.gamesskills.domain.repositories

import com.hamilton.gamesskillst.domain.models.GameSkill

interface GameSkillsRepository {
    fun getFifaGameSkills(listener: (List<GameSkill>) -> Unit)
    fun downloadSkillsVideos(skills: List<GameSkill>)
}