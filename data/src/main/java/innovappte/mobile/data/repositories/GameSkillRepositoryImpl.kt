package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.ActionsDataSource
import innovappte.mobile.data.datasources.FiFaFirebaseCollections.SKILLS
import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.domain.models.Action
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import io.reactivex.Observable
import io.reactivex.Single


class GameSkillRepositoryImpl(
        private val fiFaGameSkillsFirebaseDataSource: FiFaGameSkillsFirebaseDataSource,
        private val videoDataSource: VideosDataSource,
        private val actionsDataSource: ActionsDataSource
) : GameSkillsRepository {

    override fun getFifaGameSkills(): Single<List<GameSkill>> {
        return fiFaGameSkillsFirebaseDataSource.getFiFaSkills()
                .flattenAsObservable { it }
                .flatMap({getActions(it.id)}, { celebration, retrievedActions ->
                    celebration.apply { actions = retrievedActions }
                }).toList()
    }

    private fun getActions(documentId: String): Observable<List<Action>> {
        return actionsDataSource.getActions(SKILLS, documentId).toObservable()
                .map { actions -> actions.sortedBy { it.index } }
    }

    override fun downloadSkillsVideos(skills: List<GameSkill>){
        val videos = skills.map { it.getVideosToDownloadList() }.flatten()
        videos.forEach { video ->
            videoDataSource.downloadVideo(video.url, video.getTargetFileName())
        }
    }
}