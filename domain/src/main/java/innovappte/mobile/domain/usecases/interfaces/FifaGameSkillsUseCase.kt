package innovappte.mobile.gamesskills.domain.usecases.interfaces

import innovappte.mobile.domain.models.GameSkill
import com.hamilton.gamesskillst.domain.models.GameSkillsResponse
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface FifaGameSkillsUseCase {
    fun getGameSkills(): Single<List<GameSkill>>
    fun downloadMainSkillVideos(skills: List<GameSkill>): Observable<List<GameSkill>>
}