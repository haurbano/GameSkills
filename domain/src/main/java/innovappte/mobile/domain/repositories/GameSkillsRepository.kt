package innovappte.mobile.gamesskills.domain.repositories

import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface GameSkillsRepository {
    fun getFifaGameSkills(): Single<List<GameSkill>>
    fun downloadSkillsVideo(skills: List<GameSkill>): Observable<List<GameSkill>>
}