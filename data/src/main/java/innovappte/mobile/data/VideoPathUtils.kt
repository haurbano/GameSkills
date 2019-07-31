package innovappte.mobile.data

import android.content.Context
import innovappte.mobile.domain.models.VideoType
import innovappte.mobile.domain.models.VideosContainer
import java.io.File

object VideoPathUtils {
    private const val VIDEOS_FOLDER_NAME = "GameSkillsVideos"

    fun getVideoFile(context: Context, videoContainer: VideosContainer, videoType: VideoType): File? {
        return when (videoType) {
            VideoType.Main -> getVideoFile(context, videoContainer.getMainVideo().getTargetFileName())
            VideoType.Ps4Alternative ->  getVideoFile(context, videoContainer.getPs4AlternativeVideo().getTargetFileName())
            VideoType.Ps4Classic ->  getVideoFile(context, videoContainer.getPs4ClassicVideo().getTargetFileName())
            VideoType.XboxClassic ->  getVideoFile(context, videoContainer.getXboxClassicVideo().getTargetFileName())
            VideoType.XboxAlterative ->  getVideoFile(context, videoContainer.getXboxAlternativeVideo().getTargetFileName())
        }
    }

    fun getVideoFile(context: Context, videoTargetFileName: String): File? {
        val folder = context.getExternalFilesDir(VIDEOS_FOLDER_NAME)
        return File(folder, videoTargetFileName)
    }
}