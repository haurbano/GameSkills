package innovappte.mobile.gamesskills.domain.usecases.interfaces

import com.hamilton.gamesskillst.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import io.reactivex.Single

interface FifaGameSkillsUseCase {
    fun getGameSkills(): Single<List<GameSkill>>
    fun downloadSkillVideos(skills: List<GameSkill>): GameSkillsResponse.VideoSkillsDownloadStatus
}