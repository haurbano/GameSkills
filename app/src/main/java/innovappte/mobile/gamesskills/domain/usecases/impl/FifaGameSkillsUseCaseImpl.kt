package innovappte.mobile.gamesskills.domain.usecases.impl

import com.hamilton.gamesskillst.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import com.hamilton.gamesskillst.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.domain.usecases.Interfaces.FifaGameSkillsUseCase
import io.reactivex.Single

class FifaGameSkillsUseCaseImpl(private val gameSkillsRepository: GameSkillsRepository): FifaGameSkillsUseCase {
    override fun getGameSkills(): Single<List<GameSkill>> {
        return Single.just(gameSkillsRepository.getFifaGameSkills())
    }

    override fun downloadSkillVideos(skills: List<GameSkill>): GameSkillsResponse.VideoSkillsDownloadStatus {
        gameSkillsRepository.downloadSkillsVideos(skills)
        return GameSkillsResponse.VideoSkillsDownloadStatus.Started
    }
}