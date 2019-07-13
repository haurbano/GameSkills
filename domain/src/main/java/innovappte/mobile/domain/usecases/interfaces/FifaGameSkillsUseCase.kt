package innovappte.mobile.gamesskills.domain.usecases.interfaces

import com.hamilton.gamesskillst.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import io.reactivex.Single

interface FifaGameSkillsUseCase {
    fun getGameSkills(listener: (List<GameSkill>) -> Unit)
    fun downloadSkillVideos(skills: List<GameSkill>): GameSkillsResponse.VideoSkillsDownloadStatus
}