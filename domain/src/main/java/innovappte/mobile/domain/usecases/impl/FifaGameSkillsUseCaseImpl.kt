package innovappte.mobile.gamesskills.domain.usecases.impl

import innovappte.mobile.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import io.reactivex.Single

class FifaGameSkillsUseCaseImpl(private val gameSkillsRepository: GameSkillsRepository): FifaGameSkillsUseCase {
    override fun getGameSkills(): Single<List<GameSkill>> {
        return gameSkillsRepository.getFifaGameSkills()
                .map { skills -> skills.sortedBy { it.index } }
    }

    override fun downloadSkillVideos(skills: List<GameSkill>): GameSkillsResponse.VideoSkillsDownloadStatus {
        gameSkillsRepository.downloadSkillsVideos(skills)
        return GameSkillsResponse.VideoSkillsDownloadStatus.Started
    }
}