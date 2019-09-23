package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.ActionsDataSource
import innovappte.mobile.data.datasources.FiFaFirebaseCollections.SKILLS
import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.domain.models.Action
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


class GameSkillRepositoryImpl(
        private val fiFaGameSkillsFirebaseDataSource: FiFaGameSkillsFirebaseDataSource,
        private val videoDataSource: VideosDataSource,
        private val actionsDataSource: ActionsDataSource
) : GameSkillsRepository {

    companion object {
        const val PATCH_VIDEOS_SIZE = 5
    }

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

    private fun downloadSkillVideo(skill: GameSkill, videoType: VideoType): Completable {
        val video = when(videoType) {
            VideoType.Main -> skill.getMainVideo()
            VideoType.Ps4Classic -> skill.getPs4ClassicVideo()
            VideoType.Ps4Alternative -> skill.getPs4AlternativeVideo()
            VideoType.XboxClassic -> skill.getXboxClassicVideo()
            VideoType.XboxAlterative -> skill.getXboxAlternativeVideo()
        }
        return videoDataSource.downloadVideo(video.url, video.getTargetFileName())
    }

    override fun downloadSkillsVideo(skills: List<GameSkill>): Observable<List<GameSkill>> {
        val observableList = arrayListOf<Observable<GameSkill>>()

        for (skill in skills) {
            val videoDownloadObservable = downloadSkillVideo(skill, VideoType.Main)
                    .andThen(Observable.just(skill))

            observableList.add(videoDownloadObservable)
        }
        return Observable.concat(observableList)
                .buffer(PATCH_VIDEOS_SIZE)
    }
}