package innovappte.mobile.data

import android.content.Context
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.domain.models.VideosContainer
import java.io.File

class VideoPathUtils(private val context: Context) {
    companion object {
        private const val VIDEOS_FOLDER_NAME = "GameSkillsVideos"
    }

    fun getVideoFile(videoContainer: VideosContainer, videoType: VideoType): File? {
        return when (videoType) {
            VideoType.Main -> getVideoFile(videoContainer.getMainVideo().getTargetFileName())
            VideoType.Ps4Alternative ->  getVideoFile(videoContainer.getPs4AlternativeVideo().getTargetFileName())
            VideoType.Ps4Classic ->  getVideoFile(videoContainer.getPs4ClassicVideo().getTargetFileName())
            VideoType.XboxClassic ->  getVideoFile(videoContainer.getXboxClassicVideo().getTargetFileName())
            VideoType.XboxAlterative ->  getVideoFile(videoContainer.getXboxAlternativeVideo().getTargetFileName())
        }
    }

    fun getVideoFile(videoTargetFileName: String): File? {
        val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
        return File(folder, videoTargetFileName)
    }
}