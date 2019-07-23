package innovappte.mobile.data.repositories

import android.app.DownloadManager
import android.content.Context

import innovappte.mobile.gamesskills.domain.repositories.GameSkillsRepository
import android.content.Context.DOWNLOAD_SERVICE
import android.net.Uri
import android.util.Log
import innovappte.mobile.domain.models.GameSkill
import innovappte.mobile.data.datasources.FiFaGameSkillsFirebaseDataSource
import innovappte.mobile.data.mappers.GameSkillsMapper
import innovappte.mobile.gamesskills.data.VideoPathUtils
import io.reactivex.Single


class GameSkillRepositoryImpl(
        private val context: Context,
        private val fiFaGameSkillsFirebaseDataSource: FiFaGameSkillsFirebaseDataSource,
        private val gameSkillsMapper: GameSkillsMapper
) : GameSkillsRepository {

    override fun getFifaGameSkills(): Single<List<GameSkill>> {
        return fiFaGameSkillsFirebaseDataSource.getFiFaSkills()
                .map{ gameSkillsMapper(it) }
    }

    override fun downloadSkillsVideos(skills: List<GameSkill>){
        skills.forEach { skill ->
            if (!alreadyDownloaded(skill, VideoType.Skill)) downloadVideo(skill, VideoType.Skill)
            if (!alreadyDownloaded(skill, VideoType.Ps4Classic)) downloadVideo(skill, VideoType.Ps4Classic)
            if (!alreadyDownloaded(skill, VideoType.Ps4Alternative)) downloadVideo(skill, VideoType.Ps4Alternative)
        }
    }

    private fun downloadVideo(skill: GameSkill, videoType: VideoType) {
        Log.d("GameSkills Debug", "Downloading new video - ${skill.name.default}")
        val downloadManager = context.getSystemService(DOWNLOAD_SERVICE) as DownloadManager?
        val request = buildRequest(skill, videoType)
        if (request != null) {
            downloadManager?.enqueue(request)
        }
    }

    private fun buildRequest(skill: GameSkill, videoType: VideoType): DownloadManager.Request? {
        val uri = Uri.parse(getVideoUrl(skill, videoType))
        val destinationUri = Uri.fromFile(VideoPathUtils.getVideoFile(context, skill, videoType))
        return try {
            DownloadManager.Request(uri).setDestinationUri(destinationUri)
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    private fun alreadyDownloaded(skill: GameSkill, videoType: VideoType): Boolean {
        val file = VideoPathUtils.getVideoFile(context, skill, videoType)
        return file?.exists() ?: false
    }

    private fun getVideoUrl(skill: GameSkill, videoType: VideoType): String {
        return when (videoType) {
            VideoType.Skill -> skill.skillVideo
            VideoType.Ps4Classic -> skill.ps4ControlClassicVideo
            VideoType.Ps4Alternative -> skill.ps4ControlAlternativeVideo
        }
    }

    enum class VideoType {
        Skill, Ps4Classic, Ps4Alternative
    }

}