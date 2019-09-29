package innovappte.mobile.gamesskills.domain.usecases.impl

import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import innovappte.mobile.gamesskills.domain.usecases.interfaces.FifaGameSkillsUseCase
import io.reactivex.Observable
import io.reactivex.Single

class FifaGameSkillsUseCaseImpl(private val gameSkillsRepository: GameSkillsRepository): FifaGameSkillsUseCase {
    override fun getGameSkills(): Single<List<GameSkill>> {
        return gameSkillsRepository.getFifaGameSkills()
                .map { skills ->
                    skills.sortedBy { it.index }
                        .filter { it.name.default.toUpperCase() != "TEST" }
                }
    }


    override fun downloadMainSkillVideos(skills: List<GameSkill>): Observable<List<GameSkill>> {
        return gameSkillsRepository.downloadSkillsVideo(skills)
    }
}