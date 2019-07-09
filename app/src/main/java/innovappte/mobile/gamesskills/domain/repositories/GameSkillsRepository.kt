package innovappte.mobile.gamesskills.domain.repositories

import com.hamilton.gamesskillst.domain.models.GameSkill

interface GameSkillsRepository {
    fun getFifaGameSkills(): List<GameSkill>
    fun downloadSkillsVideos(skills: List<GameSkill>)
}