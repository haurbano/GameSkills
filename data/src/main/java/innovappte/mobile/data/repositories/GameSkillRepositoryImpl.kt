package innovappte.mobile.data.repositories

import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.datasources.VideosDataSource
import innovappte.mobile.data.mappers.GameSkillsMapper
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import io.reactivex.Single


class GameSkillRepositoryImpl(
        private val fiFaGameSkillsFirebaseDataSource: FiFaGameSkillsFirebaseDataSource,
        private val videoDataSource: VideosDataSource,
        private val gameSkillsMapper: GameSkillsMapper
) : GameSkillsRepository {

    override fun getFifaGameSkills(): Single<List<GameSkill>> {
        return fiFaGameSkillsFirebaseDataSource.getFiFaSkills()
                .map{ gameSkillsMapper(it) }
    }

    override fun downloadSkillsVideos(skills: List<GameSkill>){
        val videos = skills.map { it.getVideosToDownloadList() }
                .flatten()
        videos.forEach { video ->
            videoDataSource.downloadVideo(video.url, video.getTargetFileName())
        }
    }
}